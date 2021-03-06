/*
 Copyright (c) 2015 Ahomé Innovation Technologies. All rights reserved.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package com.ait.toolkit.hopscotch.client;

import java.util.Random;

import com.ait.toolkit.core.client.Function;
import com.ait.toolkit.core.client.Util;
import com.ait.toolkit.hopscotch.client.resources.HopScotchResources;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;

/**
 * Main GwtTour class
 * 
 * @author Alain Ekambi
 * 
 */
public class HopScotch {

	private HopScotch() {

	}

	static {
		load();
	}

	/**
	 * Check if the script is loaded
	 * 
	 * @return
	 */
	public static native boolean isLoaded() /*-{
		return $wnd.hopscotch !== undefined;
	}-*/;

	/**
	 * Load the javasccript and the stylesheet by using the ScriptInjector
	 */
	private static void load() {
		if (!isLoaded()) {
			HopScotchResources resources = GWT.create(HopScotchResources.class);
			Util.injectJs(resources.js());
			Util.injectStyle(resources.css().getText().replaceAll("../img/sprite-green.png", resources.sprite().getSafeUri().asString()));
		}
	}

	/**
	 * Actually starts the tour.
	 * 
	 * @param tour
	 */
	public static void startTour(Tour tour) {
		_startTour(tour.getPeer());
	}

	/**
	 * Actually starts the tour. Optional stepNum argument specifies what step
	 * to start at.
	 * 
	 * @param tour
	 * @param stepIndex
	 */
	public static void startTour(Tour tour, int stepIndex) {
		_startTour(tour.getPeer(), stepIndex);
	}

	/**
	 * Skips to a given step in the tour
	 * 
	 * @param index
	 */
	public static native void showStep(int index)/*-{
		$wnd.hopscotch.showStep(index);
	}-*/;

	/**
	 * Goes back one step in the tour
	 */
	public static native void prevStep()/*-{
		$wnd.hopscotch.prevStep();
	}-*/;

	/**
	 * Goes forward one step in the tour
	 */
	public static native void nextStep()/*-{
		$wnd.hopscotch.nextStep();
	}-*/;

	public static native int getCurrStepNum()/*-{
		return $wnd.hopscotch.getCurrStepNum();
	}-*/;

	/**
	 * Checks for tour state saved in sessionStorage/cookies and returns the
	 * state if it exists. Use this method to determine whether or not you
	 * should resume a tour.
	 * 
	 * @return
	 */
	public static native String getState()/*-{
		return $wnd.hopscotch.getState();
	}-*/;

	private static native JavaScriptObject _getCurrTour()/*-{
		return $wnd.hopscotch.getCurrTour()
	}-*/;

	/**
	 * Ends the current tour.
	 */
	public static native void endTour()/*-{
		$wnd.hopscotch.endTour();
	}-*/;

	/**
	 * Resets i18n strings to original default values.
	 */
	public static native void restoreDefaultI18N()/*-{
		$wnd.hopscotch.restoreDefaultI18N();
	}-*/;

	/**
	 * Ends the current tour. If clearCookie is set to false, the tour state is
	 * preserved. Otherwise, if clearCookie is set to true or is not provided,
	 * the tour state is cleared.
	 */
	public static native void endTour(boolean clearCookie)/*-{
		$wnd.hopscotch.endTour(clearCookie);
	}-*/;

	/**
	 * Creates callout referenced by an id.
	 * 
	 * @param callout
	 */
	public static void createCallOut(CallOut callout) {
		_createCallOut(callout.getPeer());
	}

	public static void removeCallOut(CallOut callout) {
		removeCallOut(callout.getId());
	}

	public static native void removeCallOut(String id)/*-{
		$wnd.hopscotch.getCalloutManager().removeCallOut(id);
	}-*/;

	/**
	 * Removes all registered callouts from the page.
	 */
	public static native void removeAllCallOuts()/*-{
		$wnd.hopscotch.getCalloutManager().removeAllCallouts();
	}-*/;

	public static String generateId() {
		Random random = new Random();
		return Long.toString(Math.abs(random.nextLong()), 36);
	}

	/**
	 * Adds a callback for one of the event types. Valid event types are: start,
	 * end, next, prev, show, close, error
	 * 
	 * @param event
	 *            , the event to listen to
	 * @param callback
	 *            ,the callback to call when the event occurs.
	 */

	public static native void listen(String event, Function callback)/*-{
		$wnd.hopscotch.listen(event, function() {
			callback.@com.ait.toolkit.core.client.Function::execute()();
		});
	}-*/;

	public static native void unlisten(String event, Function callback)/*-{
		$wnd.hopscotch.unlisten(event, function() {
			callback.@com.ait.toolkit.core.client.Function::execute()();
		});
	}-*/;

	private static native void _startTour(JavaScriptObject tour)/*-{
		$wnd.hopscotch.startTour(tour);
	}-*/;

	private static native void _startTour(JavaScriptObject tour, int stepIndex)/*-{
		$wnd.hopscotch.startTour(tour, stepIndex);
	}-*/;

	private static native void _createCallOut(JavaScriptObject callout)/*-{
		$wnd.hopscotch.getCalloutManager().createCallout(callout);
	}-*/;

}
