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


import parceirosDaEstrada.web.client.ControleDeAcesso;
import parceirosDaEstrada.web.client.ControleDeAcessoAsync;
import parceirosDaEstrada.web.client.TelaUsuarioLogado;
import sistema.SistemaWebMain;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;



public class PainelCadastrarParceiros {


	private RootPanel rootPanel;
	private Label textError;
	private TextBox login, nome, endereco, emailBox;
	private PasswordTextBox senha, confirmacaoSenha;
	private Button concluirCadastroButton;
	private final ControleDeAcessoAsync controleAcesso = GWT.create(ControleDeAcesso.class);

	
	public PainelCadastrarParceiros(RootPanel rootPanel, Label textError) {
		this.rootPanel = rootPanel;
		this.textError = textError;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public CaptionPanel carregaPainel() {
		CaptionPanel painelDeCadastro = new CaptionPanel("Cadastre-se Gratuitamente: ");
		painelDeCadastro.setSize("590px", "490px");

		final LayoutPanel layoutPanel = new LayoutPanel();
		painelDeCadastro.setContentWidget(layoutPanel);
		layoutPanel.setSize("554px", "463px");

		Label tlbrtxtmLogin = new Label("Login:");
		layoutPanel.add(tlbrtxtmLogin);
		layoutPanel.setWidgetLeftWidth(tlbrtxtmLogin, 213.0, Unit.PX, 56.0,Unit.PX);
		layoutPanel.setWidgetTopHeight(tlbrtxtmLogin, 59.0, Unit.PX, 21.0, Unit.PX);
		tlbrtxtmLogin.setSize("40px", "21px");

		login = new TextBox();
		layoutPanel.add(login);
		layoutPanel.setWidgetRightWidth(login, 87.0, Unit.PX, 210.0, Unit.PX);
		layoutPanel.setWidgetTopBottom(login, 52.0, Unit.PX, 381.0, Unit.PX);
		login.setSize("200px", "20px");

		Label tlbrtxtmSenha = new Label("Senha:");
		layoutPanel.add(tlbrtxtmSenha);
		layoutPanel.setWidgetLeftWidth(tlbrtxtmSenha, 203.0, Unit.PX, 50.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(tlbrtxtmSenha, 126.0, Unit.PX, 21.0, Unit.PX);
		tlbrtxtmSenha.setSize("50px", "21px");

		senha = new PasswordTextBox();
		layoutPanel.add(senha);
		layoutPanel.setWidgetRightWidth(senha, 87.0, Unit.PX, 210.0, Unit.PX);
		layoutPanel.setWidgetTopBottom(senha, 117.0, Unit.PX, 316.0, Unit.PX);
		senha.setSize("200px", "20px");

		Label tlbrtxtmConfirmeASenha = new Label( "Confirme a Senha:");
		layoutPanel.add(tlbrtxtmConfirmeASenha);
		tlbrtxtmConfirmeASenha.setSize("120px", "21px");
		layoutPanel.setWidgetLeftWidth(tlbrtxtmConfirmeASenha, 133.0, Unit.PX, 120.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(tlbrtxtmConfirmeASenha, 175.0, Unit.PX, 21.0, Unit.PX);

		confirmacaoSenha = new PasswordTextBox();
		layoutPanel.add(confirmacaoSenha);
		confirmacaoSenha.setSize("200px", "20px");
		layoutPanel.setWidgetRightWidth(confirmacaoSenha, 87.0, Unit.PX, 210.0,
				Unit.PX);
		layoutPanel.setWidgetTopBottom(confirmacaoSenha, 168.0, Unit.PX, 265.0,
				Unit.PX);

		Label tlbrtxtmNome = new Label("Nome:");
		layoutPanel.add(tlbrtxtmNome);
		tlbrtxtmNome.setSize("40px", "21px");
		layoutPanel.setWidgetLeftWidth(tlbrtxtmNome, 207.0, Unit.PX, 100.0,
				Unit.PX);
		layoutPanel.setWidgetTopHeight(tlbrtxtmNome, 243.0, Unit.PX, 21.0,
				Unit.PX);

		nome = new TextBox();
		layoutPanel.add(nome);
		nome.setSize("200px", "20px");
		layoutPanel.setWidgetRightWidth(nome, 87.0, Unit.PX, 210.0, Unit.PX);
		layoutPanel.setWidgetTopBottom(nome, 236.0, Unit.PX, 197.0, Unit.PX);

		Label tlbrtxtmEndereo = new Label("Endere\u00E7o:");
		layoutPanel.add(tlbrtxtmEndereo);
		tlbrtxtmEndereo.setSize("60px", "21px");
		layoutPanel.setWidgetLeftRight(tlbrtxtmEndereo, 187.0, Unit.PX, 307.0,
				Unit.PX);
		layoutPanel.setWidgetTopHeight(tlbrtxtmEndereo, 286.0, Unit.PX, 21.0,
				Unit.PX);

		textError = new Label("");
		textError.setStyleName("ext-el-mask-msg div");
		layoutPanel.add(textError);
		textError.setSize("200px", "25px");
		layoutPanel.setWidgetLeftRight(textError, 261.0, Unit.PX, 87.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(textError, -500.0, Unit.PX, 36.0, Unit.PX);
		
		endereco = new TextBox();
		layoutPanel.add(endereco);
		endereco.setSize("200px", "20px");
		layoutPanel
				.setWidgetRightWidth(endereco, 87.0, Unit.PX, 210.0, Unit.PX);
		layoutPanel.setWidgetTopBottom(endereco, 277.0, Unit.PX, 156.0, Unit.PX);

		Label tlbrtxtmEmail = new Label("Email:");
		layoutPanel.add(tlbrtxtmEmail);
		tlbrtxtmEmail.setSize("40px", "21px");
		layoutPanel.setWidgetLeftWidth(tlbrtxtmEmail, 207.0, Unit.PX, 40.0,
				Unit.PX);
		layoutPanel.setWidgetTopHeight(tlbrtxtmEmail, 330.0, Unit.PX, 21.0,
				Unit.PX);

		emailBox = new TextBox();
		layoutPanel.add(emailBox);
		emailBox.setSize("200px", "20px");
		layoutPanel
				.setWidgetLeftRight(emailBox, 257.0, Unit.PX, 87.0, Unit.PX);
		layoutPanel.setWidgetTopBottom(emailBox, 321.0, Unit.PX, 112.0, Unit.PX);

		concluirCadastroButton = new Button();
		concluirCadastroButton.setText("Concluir");
		layoutPanel.add(concluirCadastroButton);
		layoutPanel.setWidgetLeftWidth(concluirCadastroButton, 402.0, Unit.PX,
				95.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(concluirCadastroButton, 373.0, Unit.PX,
				28.0, Unit.PX);
		concluirCadastroButton.setSize("120", "10");
		
		concluirCadastroButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				try {
					// criar metodo compara senhas.............
					if (!login.getText().equals("")
							&& !senha.getText().equals("")) {
						if (!senha.getText().equals(confirmacaoSenha.getText()))
							throw new Exception(
									"O campos senha e confirma\u00E7\u00E3o de senha nao conrrespondem!");
					}
					SistemaWebMain.getSistema().criarUsuario(login.getText(),
							senha.getText(), nome.getText(),
							endereco.getText(), emailBox.getText());
							
							gravaUsuario(login.getText(),
									senha.getText(), nome.getText(),
									endereco.getText(), emailBox.getText());
					loginAction(login.getText(), senha.getText());
				} catch (Exception e) {
					textError.setText(e.getMessage().replace("á", "\u00E1") + "!");
					layoutPanel.setWidgetLeftRight(textError, 261.0, Unit.PX, 87.0, Unit.PX);
					layoutPanel.setWidgetTopBottom(textError, 407.0, Unit.PX, 20.0, Unit.PX);
				
				}
			}
		});
		return painelDeCadastro;
	}
	private void gravaUsuario(String login, String senha,String nome,
			String endereco, String email) {
		try { 
			AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>(){

				public void onFailure(Throwable caught) {
					//Window.alert(caught.getMessage() + "!");

				}

				public void onSuccess(Boolean result) {
					if (true == result)
						Window.alert("Sucess!");

				}
			};
			controleAcesso.GravaUsuario(login, senha, nome, endereco, email,  callback);
		} catch (Exception ex) {
			Window.alert(ex.getMessage()); 
		}

	}
	private void loginAction(String login, String senha) throws Exception {
		String idUsuarioLogado = SistemaWebMain.getSistema().abrirSessao(login, senha);
		rootPanel.clear();
		TelaUsuarioLogado telaUsuarioLogado = new TelaUsuarioLogado(idUsuarioLogado, login);
		telaUsuarioLogado.onModuleLoad();
	}
}

