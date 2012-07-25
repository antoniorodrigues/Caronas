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

import java.util.Date;
import java.util.List;

import parceirosDaEstrada.web.client.paineis.PainelCadastrarParceiros;
import parceirosDaEstrada.web.client.paineis.PainelOProjeto;
import sistema.SistemaWebMain;

import componentesdosistema.Carona;

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

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ParceirosDaEstrada implements EntryPoint {
	private RootPanel rootPanel;
	private Button loginButton, cadastroButton, cancelarCadastroButton;
	private TextBox campoLogin;
	private PasswordTextBox campoSenha;
	private Label textError;
	private Widget painelCentral;
	private CaptionPanel painelDeCadastro;
	private static ParceirosDaEstrada  instanceParceirosDaEstrada = null;
	
	public static ParceirosDaEstrada getInstance(){
		if (instanceParceirosDaEstrada == null){
			instanceParceirosDaEstrada = new ParceirosDaEstrada();
			try {
				SistemaWebMain.start();
			} catch (Exception e) {
				Window.alert(e.getMessage()+ " Erro ao iniciar!");
			}
		}return instanceParceirosDaEstrada;
	}


	public LayoutPanel carregaPainelCaronas() throws Exception {
		LayoutPanel painelDeCaronas = new LayoutPanel();
		painelDeCaronas.setSize("590px", "490px");
		final List<Carona> CONTACTS = SistemaWebMain.getSistema().localizarCarona("", "");

		DateCell dateCell = new DateCell();

		TabPanel tabPanel = new TabPanel();
		tabPanel.setSize("586px", "485px");
		CellTable<Carona> table = new CellTable<Carona>();
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		final SingleSelectionModel<Carona> selectionModel = new SingleSelectionModel<Carona>();
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						Carona selected = selectionModel.getSelectedObject();
						if (selected != null) {
							Window.alert(selected.toString()+"\n  Cadastre agora mesmo e solicite uma vaga!");
						}
					}
				}); 
		TextColumn<Carona> nameColumn = new TextColumn<Carona>() {
			@Override
			public String getValue(Carona object) {
				return object.getOrigem();
			}
		};
		table.addColumn(nameColumn, "Origem");
		Column<Carona, Date> dateColumn = new Column<Carona, Date>(dateCell) {
			@SuppressWarnings("deprecation")
			@Override
			public Date getValue(Carona object) {
				String[] data = object.getData().split("/");
				return new Date(Integer.parseInt(data[2])-1900,
						Integer.parseInt(data[1])-1,
						Integer.parseInt(data[0]));
			
			}
		};
		table.addColumn(dateColumn, "Data");

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
		table.setRowData(0, CONTACTS);
		table.setSize("572px", "446px");

		tabPanel.add((new PainelOProjeto()).carregaPainel(), "O Projeto", false);
		tabPanel.selectTab(0);
		painelDeCaronas.add(tabPanel);
		return painelDeCaronas;
	}

	private void loginAction(String login, String senha) throws Exception {
		String idUsuarioLogado = SistemaWebMain.getSistema().abrirSessao(login, senha);
		textError.setText("");
		rootPanel.clear();
		TelaUsuarioLogado telaUsuarioLogado = new TelaUsuarioLogado(idUsuarioLogado, login);
		telaUsuarioLogado.onModuleLoad();
	}

	public void onModuleLoad() {
		instanceParceirosDaEstrada = ParceirosDaEstrada.getInstance();
		instanceParceirosDaEstrada.carregaHomePage();
	}
	
	public void carregaHomePage(){
		rootPanel = RootPanel.get();

		Image image_1 = new Image("parceirosDaEstrada.web.ImageViewer/js/ext/resources/images/vista/basic-dialog/loginBox.png");
		rootPanel.add(image_1, 21, 85);
		image_1.setSize("237px", "267px");

		Label lblPasseirosDaEstrada = new Label(" Bem Vindo ao Parceiros da Estrada!  O mais novo portal\r\n de Caronas do Estado.  De forma segura voc\u00EA se conecta  com \r\noutros  usu\u00E1rios do  sistema  e encontra caronas para  qualquer\r\nlugar.  Dessa  forma voc\u00EA  pode ajudar o planeta diminuindo o \r\nn\u00FAmero de ve\u00EDculos nas estradas e economizando combust\u00EDvel.\r\n\r\n\r\n\t     Venha  fazer amigos,   economizar  e  contribuir  com  a\r\n natureza.    Cadastre-se agora    mesmo,  crie e   solicite  novas \r\ncaronas!");
		lblPasseirosDaEstrada.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		rootPanel.add(lblPasseirosDaEstrada, 33, 420);
		lblPasseirosDaEstrada.setSize("361px", "140px");

		Image image = new Image("parceirosDaEstrada.web.ImageViewer/js/ext/resources/images/vista/basic-dialog/logo.PNG");
		rootPanel.add(image, 21, 619);
		image.setSize("399px", "140px");

		Image image_2 = new Image("parceirosDaEstrada.web.ImageViewer/js/ext/resources/images/vista/basic-dialog/logoAnimation.gif");
		rootPanel.add(image_2, 527, -34);
		image_2.setSize("450px", "284px");

		LayoutPanel layoutLogin = new LayoutPanel();
		rootPanel.add(layoutLogin, 0, 85);
		layoutLogin.setSize("338px", "284px");
		
		Label loginText = new Label("Login");
		layoutLogin.add(loginText);
		layoutLogin.setWidgetLeftWidth(loginText, 36.0, Unit.PX, 62.0, Unit.PX);
		layoutLogin.setWidgetTopHeight(loginText, 40.0, Unit.PX, 15.0, Unit.PX);
		
		Label senhaText = new Label("Senha");
		layoutLogin.add(senhaText);
		layoutLogin.setWidgetLeftWidth(senhaText, 36.0, Unit.PX, 62.0, Unit.PX);
		layoutLogin.setWidgetTopHeight(senhaText, 96.0, Unit.PX, 21.0, Unit.PX);

		campoLogin = new TextBox();
		layoutLogin.add(campoLogin);
		layoutLogin.setWidgetLeftWidth(campoLogin, 36.0, Unit.PX, 157.0, Unit.PX);
		layoutLogin.setWidgetTopHeight(campoLogin, 55.0, Unit.PX, 30.0, Unit.PX);
		campoLogin.setSize("132px", "20px");

		campoSenha = new PasswordTextBox();
		layoutLogin.add(campoSenha);
		layoutLogin.setWidgetLeftRight(campoSenha, 36.0, Unit.PX, 157.0, Unit.PX);
		layoutLogin.setWidgetTopHeight(campoSenha, 112.0, Unit.PX, 28.0, Unit.PX);
		campoSenha.setSize("132px", "20px");

		loginButton = new Button();
		layoutLogin.add(loginButton);
		layoutLogin.setWidgetLeftWidth(loginButton, 36.0, Unit.PX, 64.0, Unit.PX);
		layoutLogin.setWidgetTopHeight(loginButton, 146.0, Unit.PX, 24.0, Unit.PX);
		loginButton.setSize("120", "10");
		loginButton.setText("Entrar");
		loginButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				try {
					loginAction(campoLogin.getText(), campoSenha.getText());
				} catch (Exception e) {
					textError.setText(e.getMessage().replace("�", "\u00E1") + "!");
					textError.setVisible(true);
				}
			}
		});

		cadastroButton = new Button();
		layoutLogin.add(cadastroButton);
		layoutLogin.setWidgetLeftWidth(cadastroButton, 106.0, Unit.PX, 93.0, Unit.PX);
		layoutLogin.setWidgetTopHeight(cadastroButton, 146.0, Unit.PX, 24.0, Unit.PX);
		cadastroButton.setText("Cadastre-se");
		cadastroButton.setSize("93px", "24px");
		
		cancelarCadastroButton= new Button("Cancelar");
		cancelarCadastroButton.setSize("110px", "24px");
		
		CaptionPanel logoPanel = new CaptionPanel("");
		rootPanel.add(logoPanel, 468, 0);
		logoPanel.setSize("582px", "248px");
		
		textError = new Label("");
		layoutLogin.add(textError);
		layoutLogin.setWidgetLeftWidth(textError, 36.0, Unit.PX, 140.0, Unit.PX);
		layoutLogin.setWidgetTopHeight(textError, 176.0, Unit.PX, 21.0, Unit.PX);
		textError.setStyleName("x-tree-node");
		textError.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		textError.setSize("140px", "14px");textError.setVisible(false);
		cadastroButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
					rootPanel.remove(painelCentral);
					PainelCadastrarParceiros painelCadastro = new PainelCadastrarParceiros(rootPanel, textError);
					painelDeCadastro = painelCadastro.carregaPainel();
					//painelCentral = painelDeCadastro.carregaPainel();
					rootPanel.add(painelDeCadastro, 468, 258);
					rootPanel.add(cancelarCadastroButton, 605, 647);
		
			}
		});
		cancelarCadastroButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
					rootPanel.remove(painelDeCadastro);
					rootPanel.add(painelCentral, 468, 258);
					rootPanel.remove(cancelarCadastroButton);
		
			}
		});

		try {
			painelCentral = carregaPainelCaronas();
			rootPanel.add(painelCentral, 468, 258);
				
		} catch (Exception e) {
			Window.alert(e.getMessage() + "!" + campoSenha.getText());
	
		}
	}
}
