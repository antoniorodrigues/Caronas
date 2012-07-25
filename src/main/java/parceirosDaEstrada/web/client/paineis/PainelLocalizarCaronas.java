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
import java.util.Date;
import java.util.List;

import sistema.SistemaWebMain;

import componentesdosistema.Carona;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;



public class PainelLocalizarCaronas implements Painel {

	private TextBox campoOrigem, campoDestino;
	private String idUsuarioLogado;
	private LayoutPanel layoutLocalizarCaronas;
	public PainelLocalizarCaronas(String idUsuarioLogado) {
		this.idUsuarioLogado = idUsuarioLogado;
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public LayoutPanel carregaPainel() {

		List<Carona> Caronas = new ArrayList<Carona>();
		try {
			Caronas = SistemaWebMain.getSistema()
					.localizarCarona("", "");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		DateCell dateCell = new DateCell();
		layoutLocalizarCaronas = new LayoutPanel();

		final SingleSelectionModel<Carona> selectionModel = new SingleSelectionModel<Carona>();
		selectionModel
		.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				Carona selected = selectionModel.getSelectedObject();
				if (selected != null) {
					PainelOpcoesDeCarona painel = new PainelOpcoesDeCarona(selected, idUsuarioLogado, PainelLocalizarCaronas.this);
					layoutLocalizarCaronas.clear();
					layoutLocalizarCaronas.add(painel.carregaPainelDadosDaCarona());
					painel.onModuleLoad();

				}
			}
		});

		ScrollPanel scrollPanel = new ScrollPanel();
		layoutLocalizarCaronas.add(scrollPanel);
		layoutLocalizarCaronas.setWidgetLeftWidth(scrollPanel, 45.0, Unit.PX,
				679.0, Unit.PX);
		layoutLocalizarCaronas.setWidgetTopHeight(scrollPanel, 110.0, Unit.PX,
				410.0, Unit.PX);
		scrollPanel.setSize("675px", "410px");

		// Cria a CellTable que mostra a lista de caronas ao usuario
		final CellTable<Carona> table = new CellTable<Carona>();
		scrollPanel.setWidget(table);
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		TextColumn<Carona> origemColumn = new TextColumn<Carona>() {
			@Override
			public String getValue(Carona object) {
				return object.getOrigem();
			}
		};

		// Coluna origem
		table.addColumn(origemColumn, "Origem");

		// Coluna data
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

		// Coluna destino
		TextColumn<Carona> destinoColumn = new TextColumn<Carona>() {
			@Override
			public String getValue(Carona object) {
				return object.getDestino();
			}
		};
		table.addColumn(destinoColumn, "Destino");

		// Coluna vagas
		TextColumn<Carona> vagasColumn = new TextColumn<Carona>() {
			@Override
			public String getValue(Carona object) {
				return object.getVagas();
			}
		};
		table.addColumn(vagasColumn, "Vagas");

		table.setSelectionModel(selectionModel);
		table.setRowCount(Caronas.size(), true);

		// Push the data into the widget.
		table.setRowData(Caronas);
		table.setSize("688px", "420px");
		campoOrigem = new TextBox();
		// tabPanel.add(campoOrigem);
		layoutLocalizarCaronas.add(campoOrigem);
		layoutLocalizarCaronas.setWidgetLeftWidth(campoOrigem, 101.0, Unit.PX,
				253.0, Unit.PX);
		layoutLocalizarCaronas.setWidgetTopHeight(campoOrigem, 17.0, Unit.PX,
				32.0, Unit.PX);

		campoDestino = new TextBox();
		layoutLocalizarCaronas.add(campoDestino);
		layoutLocalizarCaronas.setWidgetLeftWidth(campoDestino, 439.0, Unit.PX,
				253.0, Unit.PX);
		layoutLocalizarCaronas.setWidgetTopHeight(campoDestino, 17.0, Unit.PX,
				32.0, Unit.PX);

		Label origemText = new Label("Origem:");
		layoutLocalizarCaronas.add(origemText);
		origemText.setSize("60px", "21px");
		layoutLocalizarCaronas.setWidgetLeftWidth(origemText, 35.0,
				Unit.PX, 60.0, Unit.PX);
		layoutLocalizarCaronas.setWidgetTopHeight(origemText, 28.0,
				Unit.PX, 21.0, Unit.PX);

		Label destinoText = new Label("Destino:");
		layoutLocalizarCaronas.add(destinoText);
		destinoText.setSize("60px", "21px");
		layoutLocalizarCaronas.setWidgetLeftWidth(destinoText, 377.0,
				Unit.PX, 60.0, Unit.PX);
		layoutLocalizarCaronas.setWidgetTopHeight(destinoText, 28.0,
				Unit.PX, 21.0, Unit.PX);

		Button buscarCaronaButton = new Button("New button");
		buscarCaronaButton.setText("Buscar Carona");
		layoutLocalizarCaronas.add(buscarCaronaButton);
		layoutLocalizarCaronas.setWidgetLeftWidth(buscarCaronaButton, 574.0,
				Unit.PX, 118.0, Unit.PX);
		layoutLocalizarCaronas.setWidgetTopHeight(buscarCaronaButton, 65.0,
				Unit.PX, 24.0, Unit.PX);

		// determina acao ao clicar o botato de buscar carona
		buscarCaronaButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				try {
					List<Carona> Caronas = SistemaWebMain.getSistema()
							.localizarCarona(campoOrigem.getText(),
									campoDestino.getText());

					table.setRowData(Caronas);
				} catch (Exception e) {
					Window.alert(e.getMessage() + "!");
				}
			}
		});

		// Push the data into the widget.
		table.setRowData(Caronas);
		Label infoMsg = new Label("Clique na carona desejada para ver mais detalhes e solicitar uma vaga.");
		layoutLocalizarCaronas.add(infoMsg);
		infoMsg.setSize("460px", "21px");
		layoutLocalizarCaronas.setWidgetLeftRight(infoMsg, 47.0, Unit.PX, 267.0, Unit.PX);
		layoutLocalizarCaronas.setWidgetTopHeight(infoMsg, 77.0, Unit.PX, 21.0, Unit.PX);
		layoutLocalizarCaronas.setSize("759px", "542px");
		return layoutLocalizarCaronas;
	}

}
