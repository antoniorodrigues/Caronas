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
import java.util.Date;
import java.util.List;

import componentesdosistema.Carona;
import controllers.SistemaWebMain;


import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.gwtext.client.widgets.ToolbarTextItem;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.VerticalSplitPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ImageViewer implements EntryPoint {
	private RootPanel rootPanel;
	private Button loginButton, cadastroButton, concluirCadastroButton;
	private TextBox campoLogin;
	private PasswordTextBox campoSenha;
	private Label textError;
	private Widget painelCentral; 
	
	//atributos campo de texto da tela de Cadastro do Usuario
	private TextBox login, nome, endereco, emailBox;
	private PasswordTextBox senha, confirmacaoSenha;

	public void loadSistema() throws Exception{
	 	SistemaWebMain.getSistema().zerarSistema();
	}
	
	private void carregaMensagemErroLogin(){
		textError = new Label("");
		textError.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		RootPanel.get().add(textError, 30, 258);
		textError.setSize("140px", "8px");

	}
	private CaptionPanel carregaPainelDeCadastro(){
		//informacoesDeCadastro = new ArrayList<String>();
		CaptionPanel painelDeCadastro = new CaptionPanel("Cadastre-se Gratuitamente: ");
		painelDeCadastro.setSize("590px", "490px");
		
		LayoutPanel layoutPanel = new LayoutPanel();
		painelDeCadastro.setContentWidget(layoutPanel);
		layoutPanel.setSize("554px", "421px");
		
		ToolbarTextItem tlbrtxtmLogin = new ToolbarTextItem("Login:");
		layoutPanel.add(tlbrtxtmLogin);
		layoutPanel.setWidgetLeftWidth(tlbrtxtmLogin, 213.0, Unit.PX, 56.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(tlbrtxtmLogin, 59.0, Unit.PX, 21.0, Unit.PX);
		tlbrtxtmLogin.setSize("40px", "21px");
		
		login = new TextBox();
		layoutPanel.add(login);
		layoutPanel.setWidgetLeftRight(login, 259.0, Unit.PX, 87.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(login, 52.0, Unit.PX, 28.0, Unit.PX);
		login.setSize("200px", "20px");
		
		ToolbarTextItem tlbrtxtmSenha = new ToolbarTextItem("Senha:");
		layoutPanel.add(tlbrtxtmSenha);
		layoutPanel.setWidgetLeftWidth(tlbrtxtmSenha, 203.0, Unit.PX, 50.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(tlbrtxtmSenha, 126.0, Unit.PX, 21.0, Unit.PX);
		tlbrtxtmSenha.setSize("50px", "21px");
		
		senha = new PasswordTextBox();
		layoutPanel.add(senha);
		layoutPanel.setWidgetLeftRight(senha, 259.0, Unit.PX, 87.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(senha, 119.0, Unit.PX, 28.0, Unit.PX);
		senha.setSize("200px", "20px");
		
		ToolbarTextItem tlbrtxtmConfirmeASenha = new ToolbarTextItem("Confirme a Senha:");
		layoutPanel.add(tlbrtxtmConfirmeASenha);
		tlbrtxtmConfirmeASenha.setSize("120px", "21px");
		layoutPanel.setWidgetLeftWidth(tlbrtxtmConfirmeASenha, 133.0, Unit.PX, 120.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(tlbrtxtmConfirmeASenha, 175.0, Unit.PX, 21.0, Unit.PX);
		
		confirmacaoSenha = new PasswordTextBox();
		layoutPanel.add(confirmacaoSenha);
		confirmacaoSenha.setSize("200px", "20px");
		layoutPanel.setWidgetLeftRight(confirmacaoSenha, 259.0, Unit.PX, 87.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(confirmacaoSenha, 168.0, Unit.PX, 28.0, Unit.PX);
		
		ToolbarTextItem tlbrtxtmNome = new ToolbarTextItem("Nome:");
		layoutPanel.add(tlbrtxtmNome);
		tlbrtxtmNome.setSize("40px", "21px");
		layoutPanel.setWidgetLeftWidth(tlbrtxtmNome, 213.0, Unit.PX, 100.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(tlbrtxtmNome, 243.0, Unit.PX, 21.0, Unit.PX);
		
		nome = new TextBox();
		layoutPanel.add(nome);
		nome.setSize("200px", "20px");
		layoutPanel.setWidgetLeftRight(nome, 259.0, Unit.PX, 87.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(nome, 236.0, Unit.PX, 28.0, Unit.PX);
		
		ToolbarTextItem tlbrtxtmEndereo = new ToolbarTextItem("Endere\u00E7o:");
		layoutPanel.add(tlbrtxtmEndereo);
		tlbrtxtmEndereo.setSize("60px", "21px");
		layoutPanel.setWidgetLeftRight(tlbrtxtmEndereo, 193.0, Unit.PX, 301.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(tlbrtxtmEndereo, 286.0, Unit.PX, 21.0, Unit.PX);
		
		endereco = new TextBox();
		layoutPanel.add(endereco);
		endereco.setSize("200px", "20px");
		layoutPanel.setWidgetLeftWidth(endereco, 259.0, Unit.PX, 208.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(endereco, 279.0, Unit.PX, 28.0, Unit.PX);
		
		ToolbarTextItem tlbrtxtmEmail = new ToolbarTextItem("Email:");
		layoutPanel.add(tlbrtxtmEmail);
		tlbrtxtmEmail.setSize("40px", "21px");
		layoutPanel.setWidgetLeftWidth(tlbrtxtmEmail, 213.0, Unit.PX, 40.0, Unit.PX);
		layoutPanel.setWidgetTopHeight		(tlbrtxtmEmail, 330.0, Unit.PX, 21.0, Unit.PX);
		
		emailBox = new TextBox();
		layoutPanel.add(emailBox);
		emailBox.setSize("200px", "20px");
		layoutPanel.setWidgetLeftWidth(emailBox, 259.0, Unit.PX, 208.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(emailBox, 323.0, Unit.PX, 28.0, Unit.PX);
		
		concluirCadastroButton = new Button();
		concluirCadastroButton.setText("Concluir");
		layoutPanel.add(concluirCadastroButton);
		layoutPanel.setWidgetLeftWidth(concluirCadastroButton, 374.0, Unit.PX, 93.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(concluirCadastroButton, 373.0, Unit.PX, 28.0, Unit.PX);
		concluirCadastroButton.setSize("120", "10");
		//RootPanel.get().add(painelCadastro, 468, 258);
		
		concluirCadastroButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				try{
					//criar metodo compara senhas.............
					if (!login.getText().equals("") && !senha.getText().equals("")){
						if (!senha.getText().equals(confirmacaoSenha.getText()))
								throw new Exception("O campos senha e confirmação de senha nao conrrespondem!");
					}
					SistemaWebMain.getSistema().
					criarUsuario(login.getText(), senha.getText(), 
							nome.getText(), endereco.getText(), emailBox.getText());
					loginAction(login.getText(), senha.getText());
				}catch (Exception e) {
					Window.alert(e.getMessage()+"!");
					//textError.setText(e.getMessage()+"!");
				}
			}
		});
		return painelDeCadastro;
	}
	public LayoutPanel carregaPainelCaronas() throws Exception {
		SistemaWebMain.start();
		LayoutPanel painelDeCaronas = new LayoutPanel();
		painelDeCaronas.setSize("590px", "490px");
		final List<Carona> CONTACTS = SistemaWebMain.getSistema().localizarCarona("", "");
		

		
		DateCell dateCell = new DateCell();
		
		TabPanel tabPanel = new TabPanel();
		tabPanel.setSize("586px", "485px");
		// Create a CellTable.
		CellTable<Carona> table = new CellTable<Carona>();
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		// Add a selection model to handle user selection.
		final SingleSelectionModel<Carona> selectionModel = new SingleSelectionModel<Carona>();
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						Carona selected = selectionModel.getSelectedObject();
						if (selected != null) {
							Window.alert(selected.toString());
						}
					}
				}); // Add a text column to show the name.
		TextColumn<Carona> nameColumn = new TextColumn<Carona>() {
			@Override
			public String getValue(Carona object) {
				return object.getOrigem();
			}
		};
		table.addColumn(nameColumn, "Origem");
		Column<Carona, Date> dateColumn = new Column<Carona, Date>(dateCell) {
			@Override
			public Date getValue(Carona object) {

				return new Date(object.getData());
			}
		};
		table.addColumn(dateColumn, "Data");

		// Add a text column to show the address.
		TextColumn<Carona> addressColumn = new TextColumn<Carona>() {
			@Override
			public String getValue(Carona object) {
				return object.getDestino();
			}
		};
		table.addColumn(addressColumn, "Destino");
		table.setSelectionModel(selectionModel);


		tabPanel.add(table, "Proximas Caronas", false);
		table.setRowCount(CONTACTS.size(), true);

		// Push the data into the widget.
		table.setRowData(0, CONTACTS);
		table.setSize("572px", "446px");
		
		LayoutPanel layoutPanel = new LayoutPanel();
		tabPanel.add(layoutPanel, "O Projeto", false);
		layoutPanel.setSize("551px", "334px");
		
		Label lblparceirosDaEstrada = new Label("\t .................................Parceiros Da Estrada..................................              - Projeto da Disciplina de Sistemas de Informa\u00E7\u00E3o............ \r\n\tDesenvolvedores: \r\nAntonio Marcos, Diego Augusto, Eduardo Rovaris, Laercio Virturino e Rodolfo Rocha\r\n\r\n\tO sistema consiste em uma rede social na web para compartilhamento de caronas.\r\n\tOs usu\u00E1rios solicitam ou divulgam uma carona, e o sistema ajuda a pare\u00E1-los.\r\n\tO sistema facilitar\u00E1 que motoristas e caroneiros encontrem as melhores op\u00E7\u00F5es\r\n\tpara uma carona, e ajudar\u00E1 na negocia\u00E7\u00E3o envolvida em solicitar uma carona, \r\n\tdo ponto de vista dos caroneiros, e em escolher os melhores caroneiros, do \r\n\tponto de vista dos motoristas.\r\n\tMelhores caroneiros podem ser definidos por v\u00E1rios crit\u00E9rios, como local de\r\n\tresid\u00EAncia, reputa\u00E7\u00E3o, ou conhecimento pr\u00E9vio.\r\n");
		layoutPanel.add(lblparceirosDaEstrada);
		layoutPanel.setWidgetLeftWidth(lblparceirosDaEstrada, 96.0, Unit.PX, 421.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblparceirosDaEstrada, 37.0, Unit.PX, 252.0, Unit.PX);
		
		Label lblNewLabel = new Label("\r\n..........Universidade Federal de Campina Grande - 2012 versao beta 1.0........");
		layoutPanel.add(lblNewLabel);
		layoutPanel.setWidgetLeftRight(lblNewLabel, 47.0, Unit.PX, 21.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblNewLabel, 310.0, Unit.PX, 24.0, Unit.PX);
		tabPanel.selectTab(0);

		painelDeCaronas.add(tabPanel);
		return painelDeCaronas;
	}
	private void loginAction(String login, String senha) throws Exception{
		SistemaWebMain.
		abrirSessao(login, senha);
		Window.alert("Bem vindo "+login+"!");textError.setText("");
					
		rootPanel.clear();
		TelaUsuarioLogado telaUsuarioLogado = new TelaUsuarioLogado();
		//painelCentral = TelaUsuarioLogado.carregaPainelCaronas();
		//rootPanel.add(telaUsuarioLogado.getRoot());
		telaUsuarioLogado.onModuleLoad();
	}

	public void onModuleLoad() {
		rootPanel = RootPanel.get();
		concluirCadastroButton = new Button();
		
		Image image_1 = new Image(
				"parceirosDaEstrada.web.ImageViewer/js/ext/resources/images/vista/basic-dialog/loginBox.png");
		rootPanel.add(image_1, 21, 85);
		image_1.setSize("237px", "267px");

		Label lblPasseirosDaEstrada = new Label(" Bem Vindo ao Parceiros da Estrada!  O mais novo portal\r\n de Caronas do Estado.  De forma segura voc\u00EA se conecta  com \r\noutros  usu\u00E1rios do  sistema  e encontra caronas para  qualquer\r\nlugar.  Dessa  forma voc\u00EA  pode ajudar o planeta diminuindo o \r\nn\u00FAmero de ve\u00EDculos nas estradas e economizando combust\u00EDvel.\r\n\r\n\r\n\t     Venha  fazer amigos,   economizar  e  contribuir  com  a\r\n natureza.    Cadastre-se agora    mesmo,  crie e   solicite  novas \r\ncaronas!");
		lblPasseirosDaEstrada.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		rootPanel.add(lblPasseirosDaEstrada, 28, 414);
		lblPasseirosDaEstrada.setSize("361px", "140px");

		Image image = new Image(
				"parceirosDaEstrada.web.ImageViewer/js/ext/resources/images/vista/basic-dialog/logo.PNG");
		rootPanel.add(image, 21, 619);
		image.setSize("399px", "140px");

		Image image_2 = new Image(
				"parceirosDaEstrada.web.ImageViewer/js/ext/resources/images/vista/basic-dialog/logoAnimation.gif");
		rootPanel.add(image_2, 527, -34);
		image_2.setSize("450px", "284px");

		LayoutPanel layoutLogin = new LayoutPanel();
		rootPanel.add(layoutLogin, 0, 85);
		layoutLogin.setSize("338px", "284px");
		
				campoLogin = new TextBox();
				layoutLogin.add(campoLogin);
				layoutLogin.setWidgetLeftWidth(campoLogin, 36.0, Unit.PX, 157.0,
						Unit.PX);
				layoutLogin
						.setWidgetTopHeight(campoLogin, 55.0, Unit.PX, 30.0, Unit.PX);
				campoLogin.setSize("132px", "20px");
		
		campoSenha = new PasswordTextBox();
		layoutLogin.add(campoSenha);
		layoutLogin.setWidgetLeftRight(campoSenha, 36.0, Unit.PX, 157.0,
				Unit.PX);
		layoutLogin.setWidgetTopHeight(campoSenha, 112.0, Unit.PX, 28.0,
				Unit.PX);
		campoSenha.setSize("132px", "20px");

		ToolbarTextItem loginText = new ToolbarTextItem("Login");
		layoutLogin.add(loginText);
		layoutLogin
				.setWidgetLeftWidth(loginText, 36.0, Unit.PX, 100.0, Unit.PX);
		layoutLogin.setWidgetTopHeight(loginText, 32.0, Unit.PX, 21.0, Unit.PX);
		loginText.setSize("100px", "21px");

		ToolbarTextItem toolbarTextItem = new ToolbarTextItem("Senha");
		layoutLogin.add(toolbarTextItem);
		layoutLogin.setWidgetLeftWidth(toolbarTextItem, 36.0, Unit.PX, 100.0,
				Unit.PX);
		layoutLogin.setWidgetTopHeight(toolbarTextItem, 89.0, Unit.PX, 21.0,
				Unit.PX);
		toolbarTextItem.setSize("100px", "21px");
		
				loginButton = new Button();
				layoutLogin.add(loginButton);
				layoutLogin.setWidgetLeftWidth(loginButton, 36.0, Unit.PX, 64.0,
						Unit.PX);
				layoutLogin.setWidgetTopHeight(loginButton, 146.0, Unit.PX, 24.0,
						Unit.PX);
				loginButton.setSize("120", "10");
				loginButton.setText("Entrar");
				loginButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						try{
							loginAction(campoLogin.getText(), campoSenha.getText());
						}catch (Exception e) {
							//Window.alert(e.getMessage()+"!");
							textError.setText(e.getMessage()+"!");
						}
					}
				});

		cadastroButton = new Button();
		layoutLogin.add(cadastroButton);
		layoutLogin.setWidgetLeftWidth(cadastroButton, 106.0, Unit.PX, 93.0, Unit.PX);
		layoutLogin.setWidgetTopHeight(cadastroButton, 146.0, Unit.PX, 24.0, Unit.PX);
		cadastroButton.setText("Cadastre-se");
		cadastroButton.setSize("93px", "24px");
		cadastroButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				try{
					//painelPrincipal = carregaPainelDeCadastro();
					rootPanel.remove(painelCentral);
					painelCentral = carregaPainelDeCadastro();
					rootPanel.add(painelCentral, 468, 258);
				}catch (Exception e) {
					textError.setText(e.getMessage()+"!");
				}
			}
		});
		
		CaptionPanel logoPanel = new CaptionPanel("");
		//cptnpnlP.setCaptionHTML("");
		rootPanel.add(logoPanel, 468, 0);
		logoPanel.setSize("582px", "248px");

		try {
			carregaMensagemErroLogin();
			loadSistema();
			//tela principal:.......
			painelCentral = carregaPainelCaronas();
			rootPanel.add(painelCentral, 468, 258);
			//////////////

			//TelaUsuarioLogado telaUsuarioLogado = new TelaUsuarioLogado();
			//rootPanel.clear();
			//painelCentral = TelaUsuarioLogado.g;
			//rootPanel.add(telaUsuarioLogado.getRoot());
			//carregaPainelPrincipal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Window.alert(e.getMessage()+"!"+campoSenha.getText());
			e.printStackTrace();
		}
	}
}
