/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.portlet;

import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import sample.service.SampleService;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Liferay
 */
@Component(
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Sample Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=sample_portlet",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class SamplePortlet extends MVCPortlet {

	public void add(
		final ActionRequest actionRequest,
		final ActionResponse actionResponse) {

		final ThemeDisplay themeDisplay =
			(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		final int firstParameter = ParamUtil.getInteger(
			actionRequest, "firstParameter");
		final int secondParameter = ParamUtil.getInteger(
			actionRequest, "secondParameter");

		final long result = _sampleService.add(firstParameter, secondParameter);

		final long plid = themeDisplay.getPlid();

		final PortletURL portletURL = PortletURLFactoryUtil.create(
			actionRequest, "sample_portlet", plid,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter(
			"firstParameter", String.valueOf(firstParameter));
		portletURL.setParameter(
			"secondParameter", String.valueOf(secondParameter));
		portletURL.setParameter("result", String.valueOf(result));

		actionRequest.setAttribute(WebKeys.REDIRECT, portletURL.toString());
	}

	@Reference
	private SampleService _sampleService;

}