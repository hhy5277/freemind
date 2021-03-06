/*FreeMind - A Program for creating and viewing Mindmaps
 *Copyright (C) 2000-2006 Joerg Mueller, Daniel Polansky, Christian Foltin, Dimitri Polivaev and others.
 *
 *See COPYING for Details
 *
 *This program is free software; you can redistribute it and/or
 *modify it under the terms of the GNU General Public License
 *as published by the Free Software Foundation; either version 2
 *of the License, or (at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program; if not, write to the Free Software
 *Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
/*
 * Created on 05.05.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package freemind.modes.mindmapmode.actions;

import java.awt.event.ActionEvent;
import java.util.ListIterator;
import java.util.logging.Logger;

import javax.swing.Action;
import javax.swing.ImageIcon;

import freemind.controller.actions.generated.instance.CompoundAction;
import freemind.main.Tools;
import freemind.modes.mindmapmode.MindMapController;
import freemind.modes.mindmapmode.MindMapMapModel;
import freemind.modes.mindmapmode.MindMapNodeModel;
import freemind.modes.mindmapmode.actions.xml.AbstractXmlAction;
import freemind.modes.mindmapmode.actions.xml.ActionPair;
import freemind.modes.mindmapmode.actions.xml.ActorXml;

public class NodeGeneralAction extends AbstractXmlAction {
	protected final MindMapController modeController;

	SingleNodeOperation singleNodeOperation;

	private Class mDoActionClass;

	protected static Logger logger;

	/**
	 * null if you cannot provide a title that is present in the resources. Use
	 * the setName method to set your not translateble title after that. give a
	 * resource name for the icon.
	 */
	protected NodeGeneralAction(MindMapController modeController,
			final String textID, String iconPath) {
		super(null, iconPath != null ? freemind.view.ImageFactory.getInstance().createIcon(
				modeController.getResource(iconPath)) : null, modeController);
		this.modeController = modeController;
		if (textID != null) {
			setName(modeController.getText(textID));
		}

		this.singleNodeOperation = null;
		if (logger == null) {
			logger = modeController.getFrame().getLogger(
					this.getClass().getName());
		}
	}

	protected void setName(String name) {
		if (name != null) {
			putValue(Action.NAME, name);
			putValue(Action.SHORT_DESCRIPTION, Tools.removeMnemonic(name));
		}

	}

	public NodeGeneralAction(MindMapController modeController, String textID,
			String iconPath, SingleNodeOperation singleNodeOperation) {
		this(modeController, textID, iconPath);
		this.singleNodeOperation = singleNodeOperation;
	}

	public NodeGeneralAction(MindMapController modeController, String textID,
			String iconPath,
			freemind.modes.mindmapmode.actions.NodeActorXml actor) {
		this(modeController, textID, iconPath);
		addActor(actor);
	}

	/**
	 * The singleNodeOperation to set.
	 */
	public void setSingleNodeOperation(SingleNodeOperation singleNodeOperation) {
		this.singleNodeOperation = singleNodeOperation;
	}

	public void xmlActionPerformed(ActionEvent e) {
		if (singleNodeOperation != null) {
			for (ListIterator it = modeController.getSelecteds().listIterator(); it
					.hasNext();) {
				MindMapNodeModel selected = (MindMapNodeModel) it.next();
				singleNodeOperation.apply(
						(MindMapMapModel) this.modeController.getMap(),
						selected);
			}
		} else {
			// xml action:
			// Do-action
			CompoundAction doAction = new CompoundAction();
			// Undo-action
			CompoundAction undo = new CompoundAction();
			// sort selectedNodes list by depth, in order to guarantee that
			// sons are deleted first:
			for (ListIterator it = modeController.getSelecteds().listIterator(); it
					.hasNext();) {
				MindMapNodeModel selected = (MindMapNodeModel) it.next();
				ActionPair pair = getActionPair(selected);
				if (pair != null) {
					doAction.addChoice(pair.getDoAction());
					undo.addAtChoice(0, pair.getUndoAction());
				}
			}
			if (doAction.sizeChoiceList() == 0)
				return;
			modeController.doTransaction((String) getValue(NAME),
					new ActionPair(doAction, undo));
		}

	}

	/**
	 * Override, if you have a different method to get to an actionpair (see EdgeStyleAction).
	 */
	protected ActionPair getActionPair(MindMapNodeModel selected) {
		ActionPair pair = null;
		if(mDoActionClass != null) {
			ActorXml actorXml = getMindMapController().getActionRegistry().getActor(mDoActionClass);
			if (actorXml instanceof NodeActorXml) {
				NodeActorXml nodeActorXml = (NodeActorXml) actorXml;
				pair = nodeActorXml.apply(this.modeController.getMap(),
						selected);
			} else {
				throw new IllegalArgumentException("ActorXml " + actorXml + " is not a NodeActorXml.");
			}
		} else {
			throw new IllegalArgumentException("doActionClass not defined.");
		}
		return pair;
	}

	protected void setDoActionClass(Class pDoActionClass) {
		this.mDoActionClass = pDoActionClass;
	}

}
