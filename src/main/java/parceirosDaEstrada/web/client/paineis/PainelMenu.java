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
package parceirosDaEstrada.web.client.paineis;

import java.util.ArrayList;
import java.util.List;

import parceirosDaEstrada.web.client.ParceirosDaEstrada;
import sistema.SistemaWebMain;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.MenuItemSeparator;
import com.google.gwt.user.client.ui.RootPanel;



public class PainelMenu implements Painel {
	private LayoutPanel painelPrincipal;
	private List<Painel> paineis;
	private String login;
	
	public PainelMenu(LayoutPanel painelPrincipal, String idUsuario, String login) {
		this.painelPrincipal = painelPrincipal;
		this.login = login;
		this.paineis = new ArrayList<Painel>();
		paineis.add(new PainelInicial(login));
		paineis.add(new PainelOProjeto());
		paineis.add(new PainelLocalizarCaronas(idUsuario));
		paineis.add(new PainelCadastrarCaronas(idUsuario));
		paineis.add(new PainelLocalizarParceiros());
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public LayoutPanel carregaPainel(){
		
		LayoutPanel painelMenu = new LayoutPanel();
		MenuBar menuBar = new MenuBar(true);

		painelMenu.setSize("200px", "354px");
		painelMenu.add(menuBar);
		painelMenu.setWidgetLeftRight(menuBar, 0.0, Unit.PX, 0.0, Unit.PX);
		painelMenu.setWidgetTopHeight(menuBar, 0.0, Unit.PX, 357.0, Unit.PX);

		MenuItem mntmPaginaInicial = new MenuItem("Pagina Inicial", false, new alteraPainelPrincipal(painelPrincipal, 0));
		mntmPaginaInicial.setHTML("<img src=\"parceirosDaEstrada.web.ImageViewer/js/ext/resources/images/vista/basic-dialog/Menu/iconPaginaInicial.png\" width=\"189\" height=\"43\" />");
		menuBar.addItem(mntmPaginaInicial);
		mntmPaginaInicial.setSize("20px", "30px");
		
		MenuItemSeparator separator = new MenuItemSeparator();
		menuBar.addSeparator(separator);
		separator.setWidth("150px");
		
		MenuItem mntmOProjeto = new MenuItem("O Projeto", true, new alteraPainelPrincipal(painelPrincipal, 1));
		mntmOProjeto.setHTML("<img src=\"parceirosDaEstrada.web.ImageViewer/js/ext/resources/images/vista/basic-dialog/Menu/iconOProjeto.png\" width=\"189\" height=\"43\" />");
		menuBar.addItem(mntmOProjeto);
		
		MenuItemSeparator separator_1 = new MenuItemSeparator();
		menuBar.addSeparator(separator_1);
		separator_1.setWidth("150px");
		
		MenuItem menuItem = new MenuItem("Localizar Caronas", false, new alteraPainelPrincipal(painelPrincipal, 2));
		menuItem.setHTML("<img src=\"parceirosDaEstrada.web.ImageViewer/js/ext/resources/images/vista/basic-dialog/Menu/iconLocalizarCarona.png\" width=\"189\" height=\"43\" />");
		menuBar.addItem(menuItem);
		
		MenuItemSeparator separator_2 = new MenuItemSeparator();
		menuBar.addSeparator(separator_2);
		separator_2.setWidth("150px");
		
		MenuItem menuItem_1 = new MenuItem("Cadastrar Nova Carona", false, new alteraPainelPrincipal(painelPrincipal, 3));
		menuItem_1.setHTML("<img src=\"parceirosDaEstrada.web.ImageViewer/js/ext/resources/images/vista/basic-dialog/Menu/iconCadastrarCarona.png\" width=\"189\" height=\"43\" />");
		menuBar.addItem(menuItem_1);
		menuItem_1.setSize("204px", "43px");
		
		MenuItemSeparator separator_3 = new MenuItemSeparator();
		menuBar.addSeparator(separator_3);
		separator_3.setWidth("150px");
		
		MenuItem menuItem_2 = new MenuItem("Parceiros Cadastrados", false, new alteraPainelPrincipal(painelPrincipal, 4));
		menuItem_2.setHTML("<img src=\"parceirosDaEstrada.web.ImageViewer/js/ext/resources/images/vista/basic-dialog/Menu/iconParceiros.png\" width=\"189\" height=\"43\" />");
		menuBar.addItem(menuItem_2);
		
		MenuItemSeparator separator_4 = new MenuItemSeparator();
		menuBar.addSeparator(separator_4);
		separator_4.setWidth("150px");
		
		MenuItem menuItemSair = new MenuItem("Sair", false, new comandoSair());
		menuItemSair.setHTML("<img src=\"parceirosDaEstrada.web.ImageViewer/js/ext/resources/images/vista/basic-dialog/Menu/iconSair.png\" width=\"189\" height=\"43\" />");
		menuBar.addItem(menuItemSair);	
		
		return painelMenu;
	}
	public class alteraPainelPrincipal implements Command {
		private LayoutPanel painelPrincipal;
		private int indicePainel;
		public alteraPainelPrincipal(LayoutPanel painelPrincipal,
				int indicePainel){
			this.painelPrincipal = painelPrincipal;
			this.indicePainel = indicePainel;
		}

	
		public void execute(){
			painelPrincipal.clear();
			painelPrincipal.add(paineis.get(indicePainel).carregaPainel());
			
		}

	}
	public class comandoSair implements Command {
		public void execute(){
			try {
				ParceirosDaEstrada home = ParceirosDaEstrada.getInstance();//ParceirosDaEstrada.getInstance();
				SistemaWebMain.getSistema().encerrarSessao(login);
				RootPanel.get().clear();
				home.onModuleLoad();
			} catch (Exception e) {
				Window.alert(e.getMessage() + "!");
			}
		}

	}
	
}