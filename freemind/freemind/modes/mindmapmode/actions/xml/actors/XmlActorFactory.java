/*FreeMind - A Program for creating and viewing Mindmaps
*Copyright (C) 2000-2014 Joerg Mueller, Daniel Polansky, Christian Foltin, Dimitri Polivaev and others.
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

package freemind.modes.mindmapmode.actions.xml.actors;

import freemind.modes.ExtendedMapFeedback;

/**
 * Creates all XmlActors needed for the MindMapController
 * 
 * @author foltin
 * @date 16.03.2014
 */
public class XmlActorFactory {

	private ExtendedMapFeedback mMapFeedback;
	private ItalicNodeActor mActionActor;
	private BoldNodeActor mBoldActor;
	private NewChildActor mNewChildActor;
	private DeleteChildActor mDeleteChildActor;
	private PasteActor mPasteActor;
	private RemoveAllIconsActor mRemoveAllIconsActor;
	private AddIconActor mAddIconActor;
	private RemoveIconActor mRemoveIconActor;
	private CloudActor mCloudActor;
	private EdgeStyleActor mEdgeStyleActor;
	private EdgeWidthActor mEdgeWidthActor;
	private FontFamilyActor mFontFamilyActor;
	private FontSizeActor mFontSizeActor;

	public XmlActorFactory(ExtendedMapFeedback pMapFeedback) {
		mMapFeedback = pMapFeedback;
		mActionActor = new ItalicNodeActor(mMapFeedback);
		mBoldActor = new BoldNodeActor(mMapFeedback);
		mNewChildActor = new NewChildActor(mMapFeedback);
		mDeleteChildActor = new DeleteChildActor(mMapFeedback);
		mPasteActor = new PasteActor(mMapFeedback);
		mRemoveAllIconsActor = new RemoveAllIconsActor(mMapFeedback);
		mAddIconActor = new AddIconActor(mMapFeedback);
		mRemoveIconActor = new RemoveIconActor(mMapFeedback);
		mCloudActor = new CloudActor(mMapFeedback);
		mEdgeStyleActor = new EdgeStyleActor(mMapFeedback);
		mEdgeWidthActor = new EdgeWidthActor(mMapFeedback);
		mFontFamilyActor = new FontFamilyActor(mMapFeedback);
		mFontSizeActor = new FontSizeActor(mMapFeedback);
	}
	
	public ItalicNodeActor getItalicActor() {
		return mActionActor;
	}
	
	public BoldNodeActor getBoldActor() {
		return mBoldActor;
	}

	public NewChildActor getNewChildActor() {
		return mNewChildActor;
	}
	
	public DeleteChildActor getDeleteChildActor() {
		return mDeleteChildActor;
	}
	
	public PasteActor getPasteActor() {
		return mPasteActor;
	}
	
	public RemoveAllIconsActor getRemoveAllIconsActor() {
		return mRemoveAllIconsActor;
	}
	
	public AddIconActor getAddIconActor() {
		return mAddIconActor;
	}
	
	public RemoveIconActor getRemoveIconActor() {
		return mRemoveIconActor;
	}
	
	public CloudActor getCloudActor() {
		return mCloudActor;
	}

	public EdgeStyleActor getEdgeStyleActor() {
		return mEdgeStyleActor;
	}
	
	public EdgeWidthActor getEdgeWidthActor() {
		return mEdgeWidthActor;
	}
	
	public FontFamilyActor getFontFamilyActor() {
		return mFontFamilyActor;
	}

	public FontSizeActor getFontSizeActor() {
		return mFontSizeActor;
	}
}