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

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.user.client.ui.Label;



public class PainelCadastrarCaronas implements Painel {
	private String idUsuarioLogado; 
	private DatePicker datePicker;
	private TextBox origemBox, destinoBox, horaBox, vagasBox;

	public PainelCadastrarCaronas(String idUsuario) {
		this.idUsuarioLogado = idUsuario;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public CaptionPanel carregaPainel() {
		CaptionPanel cadastrarCaronaPanel = new CaptionPanel("Preencha os dados da nova carona:");
		cadastrarCaronaPanel.setSize("754px", "542px");

		LayoutPanel layoutPanel = new LayoutPanel();
		cadastrarCaronaPanel.setContentWidget(layoutPanel);
		layoutPanel.setSize("725px", "492px");
		
		Label lblOrigem = new Label("Origem:");
		layoutPanel.add(lblOrigem);
		layoutPanel.setWidgetLeftWidth(lblOrigem, 170.0, Unit.PX, 56.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblOrigem, 33.0, Unit.PX, 15.0, Unit.PX);
		
		Label destinoText = new Label("Destino: ");
		layoutPanel.add(destinoText);
		layoutPanel.setWidgetLeftWidth(destinoText, 170.0, Unit.PX, 56.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(destinoText, 73.0, Unit.PX, 15.0, Unit.PX);
		
		Label dataText = new Label("Selecione a data:");
		layoutPanel.add(dataText);
		layoutPanel.setWidgetLeftWidth(dataText, 170.0, Unit.PX, 114.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(dataText, 122.0, Unit.PX, 15.0, Unit.PX);
		
		Label label = new Label("Hor\u00E1rio (HH:mm):");
		layoutPanel.add(label);
		layoutPanel.setWidgetLeftWidth(label, 170.0, Unit.PX, 114.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(label, 313.0, Unit.PX, 15.0, Unit.PX);
		
		Label vagasText = new Label("Numero de Vagas:");
		layoutPanel.add(vagasText);
		layoutPanel.setWidgetLeftWidth(vagasText, 170.0, Unit.PX, 137.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(vagasText, 355.0, Unit.PX, 15.0, Unit.PX);
		
		Label estadoOriText = new Label("Estado: ");
		layoutPanel.add(estadoOriText);
		layoutPanel.setWidgetLeftWidth(estadoOriText, 520.0, Unit.PX, 56.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(estadoOriText, 32.0, Unit.PX, 15.0, Unit.PX);
		
		Label estadoDesText = new Label("Estado: ");
		layoutPanel.add(estadoDesText);
		layoutPanel.setWidgetLeftWidth(estadoDesText, 520.0, Unit.PX, 56.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(estadoDesText, 72.0, Unit.PX, 15.0, Unit.PX);

		origemBox = new TextBox();
		layoutPanel.add(origemBox);
		origemBox.setWidth("250px");
		layoutPanel.setWidgetLeftRight(origemBox, 235.0, Unit.PX, 230.0,
				Unit.PX);
		layoutPanel.setWidgetTopBottom(origemBox, 32.0, Unit.PX, 433.0, Unit.PX);

		destinoBox = new TextBox();
		layoutPanel.add(destinoBox);
		destinoBox.setWidth("250px");
		layoutPanel.setWidgetLeftRight(destinoBox, 235.0, Unit.PX, 230.0,
				Unit.PX);
		layoutPanel
		.setWidgetTopBottom(destinoBox, 72.0, Unit.PX, 393.0, Unit.PX);
		
				datePicker = new DatePicker();
				layoutPanel.add(datePicker);
				layoutPanel.setWidgetLeftWidth(datePicker, 311.0, Unit.PX, 142.0,
						Unit.PX);
				layoutPanel.setWidgetTopHeight(datePicker, 121.0, Unit.PX, 169.0,
						Unit.PX);

		horaBox = new TextBox();
		layoutPanel.add(horaBox);
		layoutPanel.setWidgetLeftRight(horaBox, 302.0, Unit.PX, 185.0, Unit.PX);
		layoutPanel.setWidgetTopBottom(horaBox, 312.0, Unit.PX, 153.0, Unit.PX);

		vagasBox = new TextBox();
		layoutPanel.add(vagasBox);
		layoutPanel
		.setWidgetLeftRight(vagasBox, 302.0, Unit.PX, 185.0, Unit.PX);
		layoutPanel.setWidgetTopBottom(vagasBox, 354.0, Unit.PX, 111.0, Unit.PX);

		ListBox listBoxEstadosOri = new ListBox();
		final String ESTADOS[] = {"AC", "AL", "AP", "AM", "BA",
				"CE", "DF", "ES", "GO", "MA",
				"MT", "MS", "MG", "PA", "PB",
				"PR", "PE", "PI", "RN", "RJ", "RS",
				"RO", "RR", "SC", "SP", "SE", "TO"};

		for (String itemEstado: ESTADOS){
			listBoxEstadosOri.addItem(itemEstado);			
		}
		listBoxEstadosOri.setSelectedIndex(14);
		layoutPanel.add(listBoxEstadosOri);
		layoutPanel.setWidgetLeftWidth(listBoxEstadosOri, 571.0, Unit.PX, 68.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(listBoxEstadosOri, 32.0, Unit.PX, 23.0, Unit.PX);

		ListBox listBoxEstadosDes = new ListBox();
		for (String itemEstado: ESTADOS){
			listBoxEstadosDes.addItem(itemEstado);			
		}
		listBoxEstadosDes.setSelectedIndex(14);
		layoutPanel.add(listBoxEstadosDes);
		layoutPanel.setWidgetLeftWidth(listBoxEstadosDes, 571.0, Unit.PX, 68.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(listBoxEstadosDes, 72.0, Unit.PX, 23.0, Unit.PX);
		
		
		final Label mensagemErro = new Label("");
		mensagemErro.setSize("0px", "0px");
		mensagemErro.setStyleName("ext-el-mask-msg div");
		layoutPanel.add(mensagemErro);
		layoutPanel.setWidgetLeftRight(mensagemErro, 183.0, Unit.PX,
				286.0, Unit.PX);
		layoutPanel.setWidgetTopBottom(mensagemErro, 418.0, Unit.PX,
				47.0, Unit.PX);
		mensagemErro.setVisible(false);

		Button cadastrarCaronaButton = new Button("Cadastrar Carona");
		layoutPanel.add(cadastrarCaronaButton);
		layoutPanel.setWidgetLeftWidth(cadastrarCaronaButton, 466.0, Unit.PX,
				125.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(cadastrarCaronaButton, 418.0, Unit.PX,
				24.0, Unit.PX);

		// determina acao ao clicar o botato de cadastrar a carona
		cadastrarCaronaButton.addClickHandler(new ClickHandler() {
			@SuppressWarnings("deprecation")
			public void onClick(ClickEvent event) {
				try {
					String data = "";
					if (datePicker.getValue() != null){
						data = datePicker.getValue().getDate()+"/"+	(datePicker.getValue().getMonth() + 1)+"/" + (datePicker.getValue().getYear() + 1900);
					}else{
					if (!origemBox.getText().equals("") && !destinoBox.getText().equals(""))
						throw new Exception("Selecione uma data!");
					}	
					SistemaWebMain.getSistema().cadastrarCarona(idUsuarioLogado,
							origemBox.getText(), destinoBox.getText(),
							data, horaBox.getText(), vagasBox.getText());

					mensagemErro.setVisible(false);
					mensagemErro.setSize("0px", "0px");
					mensagemErro.setText("");
					Window.alert("Carona criada com sucesso!");
					
				} catch (Exception e) {
					mensagemErro.setText(e.getMessage().replace("�", "\u00E1") + "!");
					mensagemErro.setVisible(true);
					mensagemErro.setSize("250px", "21px");
				}
			}
		});
	return cadastrarCaronaPanel;
	}
}
