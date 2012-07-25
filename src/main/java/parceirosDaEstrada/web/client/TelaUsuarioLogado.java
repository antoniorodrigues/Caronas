/*******************************************************************************
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package parceirosDaEstrada.web.client;

import java.util.ArrayList;
import java.util.List;

import parceirosDaEstrada.web.client.paineis.Painel;
import parceirosDaEstrada.web.client.paineis.PainelInicial;
import parceirosDaEstrada.web.client.paineis.PainelMenu;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TelaUsuarioLogado implements EntryPoint {
	private String idUsuarioLogado, login;
	private List<Painel> paineis;


	public TelaUsuarioLogado(String idUsuarioLogado, String login) {
		this.idUsuarioLogado = idUsuarioLogado;
		this.login = login;
		this.paineis = new ArrayList<Painel>();
	}


	public void onModuleLoad() {
		carregaComponentes();
	}
	
	public void carregaComponentes(){
		RootPanel rootPanel = RootPanel.get();

		Image imageAnimation = new Image("parceirosDaEstrada.web.ImageViewer/js/ext/resources/images/vista/basic-dialog/logoAnimation.gif");
		rootPanel.add(imageAnimation, 527, -34);
		Image imagePainel = new Image("parceirosDaEstrada.web.ImageViewer/js/ext/resources/images/vista/basic-dialog/painelPrincipal.png");
		rootPanel.add(imagePainel, 280, 260);
		imageAnimation.setSize("450px", "284px");
		imagePainel.setSize("776px", "567px");

		LayoutPanel layoutPainelPrincipal = new LayoutPanel();

		Image imageMenu = new Image("parceirosDaEstrada.web.ImageViewer/js/ext/resources/images/vista/basic-dialog/painelMenu.png");
		rootPanel.add(imageMenu, 34, 458);
		imageMenu.setSize("207px", "370px");

		CaptionPanel perfilImagePanel = new CaptionPanel("");
		rootPanel.add(perfilImagePanel, 34, 268);
		perfilImagePanel.setSize("205px", "180px");

		LayoutPanel layoutPanel_1 = new LayoutPanel();
		perfilImagePanel.setContentWidget(layoutPanel_1);
		layoutPanel_1.setSize("193px", "170px");

		Image imagePerfil = new Image("parceirosDaEstrada.web.ImageViewer/js/ext/resources/images/vista/basic-dialog/avatar.jpeg");
		layoutPanel_1.add(imagePerfil);
		imagePerfil.setSize("100", "120");
		layoutPanel_1.setWidgetLeftWidth(imagePerfil, 21.0, Unit.PX, 164.0, Unit.PX);
		layoutPanel_1.setWidgetTopHeight(imagePerfil, 11.0, Unit.PX, 170.0, Unit.PX);

		CaptionPanel logoPanel = new CaptionPanel("");
		RootPanel.get().add(logoPanel, 468, 0);
		logoPanel.setSize("582px", "248px");

		LayoutPanel layoutPanel = new LayoutPanel();
		rootPanel.add(layoutPanel, 21, 10);
		layoutPanel.setSize("441px", "237px");

		Image imageLogo = new Image("parceirosDaEstrada.web.ImageViewer/js/ext/resources/images/vista/basic-dialog/logo_2.PNG");
		layoutPanel.add(imageLogo);
		layoutPanel.setWidgetLeftWidth(imageLogo, 0.0, Unit.PX, 487.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(imageLogo, 44.0, Unit.PX, 161.0, Unit.PX);

		PainelMenu painelMenuPrincipal = new PainelMenu(layoutPainelPrincipal, idUsuarioLogado, login);

		paineis.add(new PainelInicial(login));
		paineis.add(painelMenuPrincipal);
		layoutPainelPrincipal.add(paineis.get(0).carregaPainel());
		rootPanel.add(layoutPainelPrincipal, 290, 270);			
		layoutPainelPrincipal.setSize("770px", "630px");
		
		try {
			rootPanel.add(paineis.get(1).carregaPainel(), 34, 458);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}