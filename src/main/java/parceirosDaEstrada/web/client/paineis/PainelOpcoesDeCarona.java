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

import sistema.SistemaWebMain;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import componentesdosistema.Carona;
import componentesdosistema.SolicitacaoIlegalException;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class PainelOpcoesDeCarona implements EntryPoint {
	private Button buttonSolicitarVaga,  buttonVoltar, buttonSugerirPonto;
	private TextBox pontoSugeridoBox;
	private Carona carona;
	private String idSession;
	private LayoutPanel layoutPanel;
	private PainelLocalizarCaronas pontoDeEntrada;
	
	public PainelOpcoesDeCarona(Carona carona, String idSession, PainelLocalizarCaronas pontoDeEntrada) {
		this.carona = carona;
		this.idSession = idSession;
		this.pontoDeEntrada = pontoDeEntrada;
		
	}
	
		
	public void acionarSolicitacaoAction(){
		buttonSolicitarVaga.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				try {
					SistemaWebMain.
					getSistema().solicitarVaga(idSession, carona.getID());
				Window.alert("Sua vaga foi solicitada!");
				} catch (SolicitacaoIlegalException e) {
					Window.alert(e.getMessage()+"!");
				}
				
			}
		});			
	}
	public void acionarSugestaoDePonto() {
		buttonSugerirPonto.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				try {
					SistemaWebMain.
					getSistema().sugerirPontoEncontro(idSession, carona.getID(), pontoSugeridoBox.getText());
					Window.alert("Ponto sugerido com sucesso!");
				} catch (Exception e) {
					Window.alert(e.getMessage() + "!");
				}

				
			}
		});
	}
	public void acionarCancelarAction() {
		buttonVoltar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				funcaoVoltar();
			}
		});
	}
	public void funcaoVoltar(){
		try {
			layoutPanel.clear();
			pontoDeEntrada = new PainelLocalizarCaronas(idSession);
			layoutPanel.add(pontoDeEntrada.carregaPainel());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onModuleLoad() {
		acionarSolicitacaoAction();
		acionarSugestaoDePonto();
		acionarCancelarAction();
		
	}
	public boolean isvisible(){
		return layoutPanel.isVisible();
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public LayoutPanel carregaPainelDadosDaCarona() {
		
			layoutPanel = new LayoutPanel();
			layoutPanel.setSize("783px", "522px");
			
			Label textTituloCarona = new Label("Trajeto "+carona.getTrajeto());
			layoutPanel.add(textTituloCarona);
			textTituloCarona.setSize("300px", "40px");
			layoutPanel.setWidgetLeftWidth(textTituloCarona, 156.0, Unit.PX, 242.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(textTituloCarona, 25.0, Unit.PX, 40.0, Unit.PX);
			
			CaptionPanel captionPanel = new CaptionPanel("Dados Da Carona");
			layoutPanel.add(captionPanel);
			captionPanel.setSize("640px", "260px");
			layoutPanel.setWidgetLeftRight(captionPanel, 14.0, Unit.PX, 57.0, Unit.PX);
			layoutPanel.setWidgetTopBottom(captionPanel, 0.0, Unit.PX, 183.0, Unit.PX);
			
			Label Label_1 = new Label("Dona da Carona:");
			layoutPanel.add(Label_1);
			layoutPanel.setWidgetLeftWidth(Label_1, 30.0, Unit.PX, 100.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(Label_1, 76.0, Unit.PX, 21.0, Unit.PX);
			Label_1.setSize("100px", "21px");
			
			Label Label_2 = new Label("Data:");
			layoutPanel.add(Label_2);
			layoutPanel.setWidgetLeftWidth(Label_2, 30.0, Unit.PX, 100.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(Label_2, 118.0, Unit.PX, 21.0, Unit.PX);
			Label_2.setSize("100px", "21px");
			
			Label Label_3 = new Label("Hor\u00E1rio:");
			layoutPanel.add(Label_3);
			Label_3.setSize("100px", "21px");
			layoutPanel.setWidgetLeftWidth(Label_3, 30.0, Unit.PX, 100.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(Label_3, 145.0, Unit.PX, 21.0, Unit.PX);
			
			Label Label_4 = new Label("Vagas:");
			layoutPanel.add(Label_4);
			Label_4.setSize("100px", "21px");
			layoutPanel.setWidgetLeftWidth(Label_4, 30.0, Unit.PX, 100.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(Label_4, 171.0, Unit.PX, 21.0, Unit.PX);
			
			Label Label_5 = new Label("Ponto De Encontro:");
			layoutPanel.add(Label_5);
			Label_5.setSize("120px", "21px");
			layoutPanel.setWidgetLeftRight(Label_5, 30.0, Unit.PX, 188.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(Label_5, 198.0, Unit.PX, 21.0, Unit.PX);
			
			buttonSolicitarVaga = new Button();
			buttonSolicitarVaga.setText("Solicitar Vaga");
			layoutPanel.add(buttonSolicitarVaga);
			buttonSolicitarVaga.setTitle("Solicitar vaga nessa carona");
			buttonSolicitarVaga.setSize("140px", "30px");
			layoutPanel.setWidgetLeftWidth(buttonSolicitarVaga, 208.0, Unit.PX, 140.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(buttonSolicitarVaga, 225.0, Unit.PX, 61.0, Unit.PX);
			
			buttonVoltar = new Button();
			layoutPanel.add(buttonVoltar);
			layoutPanel.setWidgetLeftWidth(buttonVoltar, 353.0, Unit.PX, 140.0, Unit.PX);
			layoutPanel.setWidgetTopBottom(buttonVoltar, 225.0, Unit.PX, 161.0, Unit.PX);
			buttonVoltar.setText("Voltar");
			buttonVoltar.setTitle("Voltar ao painel de localizar caronas");
			buttonVoltar.setSize("140px", "30px");
			
			Label textDonoCarona = new Label(carona.getDono().getNome());
			layoutPanel.add(textDonoCarona);
			textDonoCarona.setSize("100px", "21px");
			layoutPanel.setWidgetLeftWidth(textDonoCarona, 136.0, Unit.PX, 100.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(textDonoCarona, 76.0, Unit.PX, 26.0, Unit.PX);
			
			Label textData = new Label(carona.getData());
			layoutPanel.add(textData);
			textData.setSize("100px", "21px");
			layoutPanel.setWidgetLeftWidth(textData, 80.0, Unit.PX, 100.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(textData, 118.0, Unit.PX, 21.0, Unit.PX);
			
			Label textHora = new Label(carona.getHora());
			layoutPanel.add(textHora);
			textHora.setSize("100px", "21px");
			layoutPanel.setWidgetLeftWidth(textHora, 80.0, Unit.PX, 100.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(textHora, 145.0, Unit.PX, 21.0, Unit.PX);
			
			Label textVagas = new Label(carona.getVagas());
			layoutPanel.add(textVagas);
			textVagas.setSize("100px", "21px");
			layoutPanel.setWidgetLeftWidth(textVagas, 80.0, Unit.PX, 100.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(textVagas, 171.0, Unit.PX, 21.0, Unit.PX);
			
			Label textPonto = new Label("(Nenhum ponto definido)");
			if (carona.getPontoDeEncontro()!= null)
				textPonto.setText((carona.getPontoDeEncontro()));
			layoutPanel.add(textPonto);
			textPonto.setSize("300px", "21px");
			layoutPanel.setWidgetLeftWidth(textPonto, 156.0, Unit.PX, 536.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(textPonto, 198.0, Unit.PX, 21.0, Unit.PX);
			
			CaptionPanel captionPanel_1 = new CaptionPanel("Sugerir Novo Ponto de Encontro");
			layoutPanel.add(captionPanel_1);
			layoutPanel.setWidgetLeftWidth(captionPanel_1, 14.0, Unit.PX, 647.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(captionPanel_1, 280.0, Unit.PX, 133.0, Unit.PX);
			captionPanel_1.setSize("640px", "129px");
			
			pontoSugeridoBox = new TextBox();
			layoutPanel.add(pontoSugeridoBox);
			layoutPanel.setWidgetLeftRight(pontoSugeridoBox, 163.0, Unit.PX, 26.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(pontoSugeridoBox, 313.0, Unit.PX, 30.0, Unit.PX);
			pontoSugeridoBox.setSize("320px", "20px");
			
			buttonSugerirPonto = new Button();
			buttonSugerirPonto.setText("Sugerir Ponto");
			layoutPanel.add(buttonSugerirPonto);
			buttonSugerirPonto.setTitle("Sugerir um novo ponto de encontro nessa carona");
			buttonSugerirPonto.setSize("190px", "30px");
			layoutPanel.setWidgetLeftWidth(buttonSugerirPonto, 303.0, Unit.PX, 214.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(buttonSugerirPonto, 349.0, Unit.PX, 30.0, Unit.PX);
			
			Label Label_7 = new Label("Ponto de Encontro:");
			layoutPanel.add(Label_7);
			Label_7.setSize("120px", "21px");
			layoutPanel.setWidgetLeftWidth(Label_7, 33.0, Unit.PX, 120.0, Unit.PX);
			layoutPanel.setWidgetTopHeight(Label_7, 322.0, Unit.PX, 21.0, Unit.PX);
			
		return layoutPanel;
	}
}
