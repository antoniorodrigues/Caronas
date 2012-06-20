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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import componentesdosistema.Carona;
import componentesdosistema.Usuario;
import controllers.SistemaWebMain;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.OnlyToBeUsedInGeneratedCodeStringBlessedAsSafeHtml;
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
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.user.client.ui.ScrollPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TelaUsuarioLogado implements EntryPoint {

	// atributos tela de cadastro de caronas
	private DatePicker datePicker;
	private TextBox origemBox, destinoBox, horaBox, vagasBox;

	// atributos tela de localizar carona
	private TextBox campoOrigem, campoDestino;

	// private List<Carona> Caronas;
	// private CellTable<Carona> table;

	public LayoutPanel carregaPainelCaronas() throws Exception {
		LayoutPanel painelDeCaronas = new LayoutPanel();
		painelDeCaronas.setSize("794px", "629px");

		final List<Carona> Caronas = SistemaWebMain.getSistema()
				.localizarCarona("", "");

		// / Painel Localizar Caronas
		// ////////////////////////////////////////////////////////
		DateCell dateCell = new DateCell();
		LayoutPanel layoutLocalizarCaronas = new LayoutPanel();

		final SingleSelectionModel<Carona> selectionModel = new SingleSelectionModel<Carona>();
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						Carona selected = selectionModel.getSelectedObject();
						if (selected != null) {
							Window.alert("Trajeto: " + selected.getTrajeto());
						}
					}
				});

		TabPanel tabPanel = new TabPanel();
		painelDeCaronas.add(tabPanel);
		painelDeCaronas.setWidgetLeftWidth(tabPanel, 17.0, Unit.PX, 778.0,
				Unit.PX);
		painelDeCaronas.setWidgetTopHeight(tabPanel, 12.0, Unit.PX, 617.0,
				Unit.PX);
		// RootPanel.get().add(tabPanel, 468, 258);
		tabPanel.setSize("586px", "485px");

		LayoutPanel projetoPanel = new LayoutPanel();
		tabPanel.add(projetoPanel, "O Projeto", false);
		projetoPanel.setSize("529px", "334px");

		Label lblNewLabel = new Label(
				"\t .................................Parceiros Da Estrada..................................              - Projeto da Disciplina de Sistemas de Informa\u00E7\u00E3o............ \r\n\tDesenvolvedores: \r\nAntonio Marcos, Diego Augusto, Eduardo Rovaris, Laercio Virturino e Rodolfo Rocha\r\n\r\n\tO sistema consiste em uma rede social na web para compartilhamento de caronas.\r\n\tOs usu\u00E1rios solicitam ou divulgam uma carona, e o sistema ajuda a pare\u00E1-los.\r\n\tO sistema facilitar\u00E1 que motoristas e caroneiros encontrem as melhores op\u00E7\u00F5es\r\n\tpara uma carona, e ajudar\u00E1 na negocia\u00E7\u00E3o envolvida em solicitar uma carona, \r\n\tdo ponto de vista dos caroneiros, e em escolher os melhores caroneiros, do \r\n\tponto de vista dos motoristas.\r\n\tMelhores caroneiros podem ser definidos por v\u00E1rios crit\u00E9rios, como local de\r\n\tresid\u00EAncia, reputa\u00E7\u00E3o, ou conhecimento pr\u00E9vio.\r\n\r\nUniversidade Federal de Campina Grande - 2012 versao beta 1.0");
		projetoPanel.add(lblNewLabel);
		projetoPanel.setWidgetLeftWidth(lblNewLabel, 78.0, Unit.PX, 421.0,
				Unit.PX);
		projetoPanel.setWidgetTopHeight(lblNewLabel, 51.0, Unit.PX, 252.0,
				Unit.PX);

		ScrollPanel scrollPanel = new ScrollPanel();
		layoutLocalizarCaronas.add(scrollPanel);
		layoutLocalizarCaronas.setWidgetLeftRight(scrollPanel, 35.0, Unit.PX,
				11.0, Unit.PX);
		layoutLocalizarCaronas.setWidgetTopHeight(scrollPanel, 104.0, Unit.PX,
				391.0, Unit.PX);

		// Create a CellTable.
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
			@Override
			public Date getValue(Carona object) {

				return new Date(object.getData());
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
		table.setSize("100%", "373px");
		tabPanel.add(layoutLocalizarCaronas, "Localizar Caronas", false);
		layoutLocalizarCaronas.setSize("764px", "561px");
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

		ToolbarTextItem toolbarTextItem = new ToolbarTextItem("Origem:");
		layoutLocalizarCaronas.add(toolbarTextItem);
		toolbarTextItem.setSize("60px", "21px");
		layoutLocalizarCaronas.setWidgetLeftWidth(toolbarTextItem, 35.0,
				Unit.PX, 60.0, Unit.PX);
		layoutLocalizarCaronas.setWidgetTopHeight(toolbarTextItem, 28.0,
				Unit.PX, 21.0, Unit.PX);

		ToolbarTextItem toolbarTextItem_1 = new ToolbarTextItem("Destino:");
		layoutLocalizarCaronas.add(toolbarTextItem_1);
		toolbarTextItem_1.setSize("60px", "21px");
		layoutLocalizarCaronas.setWidgetLeftWidth(toolbarTextItem_1, 377.0,
				Unit.PX, 60.0, Unit.PX);
		layoutLocalizarCaronas.setWidgetTopHeight(toolbarTextItem_1, 28.0,
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
					// textError.setText(e.getMessage()+"!");
				}
			}
		});
		// ////////////////////////////////////////////////////////////////////////////////////

		// Painel Cadastrar Nova Carona
		CaptionPanel cadastrarCaronaPanel = new CaptionPanel(
				"Preencha os dados da nova carona:");
		tabPanel.add(cadastrarCaronaPanel, "Cadastrar Nova Carona", false);
		cadastrarCaronaPanel.setSize("577px", "548px");

		LayoutPanel layoutPanel = new LayoutPanel();
		cadastrarCaronaPanel.setContentWidget(layoutPanel);
		layoutPanel.setSize("556px", "442px");

		origemBox = new TextBox();
		layoutPanel.add(origemBox);
		layoutPanel.setWidgetLeftWidth(origemBox, 226.0, Unit.PX, 151.0,
				Unit.PX);
		layoutPanel.setWidgetTopHeight(origemBox, 26.0, Unit.PX, 24.0, Unit.PX);

		destinoBox = new TextBox();
		layoutPanel.add(destinoBox);
		layoutPanel.setWidgetLeftWidth(destinoBox, 226.0, Unit.PX, 151.0,
				Unit.PX);
		layoutPanel
				.setWidgetTopHeight(destinoBox, 66.0, Unit.PX, 24.0, Unit.PX);

		horaBox = new TextBox();
		layoutPanel.add(horaBox);
		layoutPanel.setWidgetLeftWidth(horaBox, 226.0, Unit.PX, 151.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(horaBox, 303.0, Unit.PX, 24.0, Unit.PX);

		datePicker = new DatePicker();
		layoutPanel.add(datePicker);
		layoutPanel.setWidgetLeftWidth(datePicker, 235.0, Unit.PX, 142.0,
				Unit.PX);
		layoutPanel.setWidgetTopHeight(datePicker, 112.0, Unit.PX, 169.0,
				Unit.PX);

		Button cadastrarCaronaButton = new Button("Cadastrar Carona");
		layoutPanel.add(cadastrarCaronaButton);
		layoutPanel.setWidgetLeftWidth(cadastrarCaronaButton, 390.0, Unit.PX,
				125.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(cadastrarCaronaButton, 409.0, Unit.PX,
				24.0, Unit.PX);

		ToolbarTextItem tlbrtxtmOrigem = new ToolbarTextItem("Origem:");
		layoutPanel.add(tlbrtxtmOrigem);
		tlbrtxtmOrigem.setSize("60px", "21px");
		layoutPanel.setWidgetLeftRight(tlbrtxtmOrigem, 160.0, Unit.PX, 313.0,
				Unit.PX);
		layoutPanel.setWidgetTopHeight(tlbrtxtmOrigem, 29.0, Unit.PX, 21.0,
				Unit.PX);

		ToolbarTextItem tlbrtxtmDestino = new ToolbarTextItem("Destino:");
		layoutPanel.add(tlbrtxtmDestino);
		tlbrtxtmDestino.setSize("60px", "21px");
		layoutPanel.setWidgetLeftWidth(tlbrtxtmDestino, 160.0, Unit.PX, 60.0,
				Unit.PX);
		layoutPanel.setWidgetTopHeight(tlbrtxtmDestino, 69.0, Unit.PX, 21.0,
				Unit.PX);

		ToolbarTextItem tlbrtxtmSelecioneAData = new ToolbarTextItem(
				"Selecione a data:");
		layoutPanel.add(tlbrtxtmSelecioneAData);
		tlbrtxtmSelecioneAData.setSize("120px", "21px");
		layoutPanel.setWidgetLeftWidth(tlbrtxtmSelecioneAData, 116.0, Unit.PX,
				115.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(tlbrtxtmSelecioneAData, 112.0, Unit.PX,
				21.0, Unit.PX);

		ToolbarTextItem tlbrtxtmHorario = new ToolbarTextItem(
				"Hor\u00E1rio (HH:mm):");
		layoutPanel.add(tlbrtxtmHorario);
		tlbrtxtmHorario.setSize("120px", "21px");
		layoutPanel.setWidgetLeftWidth(tlbrtxtmHorario, 116.0, Unit.PX, 120.0,
				Unit.PX);
		layoutPanel.setWidgetTopHeight(tlbrtxtmHorario, 303.0, Unit.PX, 21.0,
				Unit.PX);

		vagasBox = new TextBox();
		layoutPanel.add(vagasBox);
		layoutPanel
				.setWidgetLeftWidth(vagasBox, 226.0, Unit.PX, 151.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(vagasBox, 345.0, Unit.PX, 24.0, Unit.PX);

		ToolbarTextItem tlbrtxtmNumeroDeVagas = new ToolbarTextItem(
				"Numero de vagas:");
		layoutPanel.add(tlbrtxtmNumeroDeVagas);
		tlbrtxtmNumeroDeVagas.setSize("110px", "21px");
		layoutPanel.setWidgetLeftWidth(tlbrtxtmNumeroDeVagas, 110.0, Unit.PX,
				120.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(tlbrtxtmNumeroDeVagas, 348.0, Unit.PX,
				24.0, Unit.PX);

		LayoutPanel usuariosPanel = new LayoutPanel();
		tabPanel.add(usuariosPanel, "Usuarios", false);
		usuariosPanel.setSize("764px", "561px");

		CellTable<Usuario> cellTable = new CellTable<Usuario>();
		cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		usuariosPanel.add(cellTable);
		usuariosPanel.setWidgetLeftWidth(cellTable, 0.0, Unit.PX, 737.0,
				Unit.PX);
		usuariosPanel.setWidgetTopHeight(cellTable, 94.0, Unit.PX, 444.0,
				Unit.PX);
		cellTable.setSize("737px", "444px");

		TextColumn<Usuario> textColumn = new TextColumn<Usuario>() {
			public String getValue(Usuario object) {
				return object.getNome();
			}
		};
		cellTable.addColumn(textColumn, "Nome");

		TextColumn<Usuario> textColumn_1 = new TextColumn<Usuario>() {
			public String getValue(Usuario object) {
				try {
					return SistemaWebMain.getSistema().getAtributoPerfil(
							object.getLogin(), "endereco");
				} catch (Exception e) {
					return (String) null;
				}
			}
		};
		cellTable.addColumn(textColumn_1, "Endereco");
		TextColumn<Usuario> textColumn_2 = new TextColumn<Usuario>() {
			public String getValue(Usuario object) {
				return object.getEmail();
			}
		};
		cellTable.addColumn(textColumn_2, "Email");

		List<Usuario> usuarios = SistemaWebMain.getSistema().getUsuarios();
		cellTable.setRowCount(usuarios.size(), true);
		cellTable.setRowData(0, usuarios);

		// Push the data into the widget.
		table.setRowData(Caronas);
		
		
		TextBox textBox_3 = new TextBox();
		usuariosPanel.add(textBox_3);
		usuariosPanel.setWidgetLeftWidth(textBox_3, 114.0, Unit.PX, 323.0,
				Unit.PX);
		usuariosPanel.setWidgetTopHeight(textBox_3, 11.0, Unit.PX, 36.0,
				Unit.PX);

		Button button = new Button("New button");
		button.setText("Buscar Usuario");
		usuariosPanel.add(button);
		usuariosPanel
				.setWidgetLeftWidth(button, 447.0, Unit.PX, 191.0, Unit.PX);
		usuariosPanel.setWidgetTopHeight(button, 11.0, Unit.PX, 36.0, Unit.PX);

		Button sairButton = new Button("New button");
		sairButton.setText("Sair");
		painelDeCaronas.add(sairButton);
		painelDeCaronas.setWidgetLeftWidth(sairButton, 510.0, Unit.PX, 95.0,
				Unit.PX);
		painelDeCaronas.setWidgetTopHeight(sairButton, 12.0, Unit.PX, 24.0,
				Unit.PX);
		sairButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				try {
					ImageViewer home = new ImageViewer();
					RootPanel.get().clear();
					home.onModuleLoad();
				} catch (Exception e) {
					Window.alert(e.getMessage() + "!");
					// textError.setText(e.getMessage()+"!");
				}
			}
		});
		// final
		// determina acao ao clicar o botato de cadastrar a carona
		cadastrarCaronaButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				try {
					String data = "";
					if (datePicker.getValue() != null) {
						data = datePicker.getValue().getDate() + "/"
								+ (datePicker.getValue().getMonth() + 1) + "/"
								+ (datePicker.getValue().getYear() + 1900);

						SistemaWebMain.getSistema().cadastrarCarona(
								SistemaWebMain.getIDUsuario(),
								origemBox.getText(), destinoBox.getText(),
								data, horaBox.getText(), vagasBox.getText());
						Window.alert("Carona criada com sucesso!");
					} else {
						Window.alert("Selecione uma data!");
					}
				} catch (Exception e) {
					Window.alert(e.getMessage() + "!");
					// textError.setText(e.getMessage()+"!");
				}
			}
		});
		tabPanel.selectTab(0);

		// Add a text column to show the name.

		return painelDeCaronas;
	}

	public RootPanel getRoot() {
		onModuleLoad();
		return RootPanel.get();

	}

	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();

		Image image_2 = new Image(
				"parceirosDaEstrada.web.ImageViewer/js/ext/resources/images/vista/basic-dialog/logoAnimation.gif");
		rootPanel.add(image_2, 527, -34);
		image_2.setSize("450px", "284px");

		CaptionPanel logoPanel = new CaptionPanel("");
		// cptnpnlP.setCaptionHTML("");
		RootPanel.get().add(logoPanel, 468, 0);
		logoPanel.setSize("582px", "248px");
		try {
			RootPanel.get().add(carregaPainelCaronas(), 260, 256);

			LayoutPanel layoutPanel = new LayoutPanel();
			rootPanel.add(layoutPanel, 21, 10);
			layoutPanel.setSize("441px", "237px");

			Image image_1 = new Image(
					"parceirosDaEstrada.web.ImageViewer/js/ext/resources/images/vista/basic-dialog/logo_2.PNG");
			layoutPanel.add(image_1);
			layoutPanel.setWidgetLeftWidth(image_1, 0.0, Unit.PX, 487.0,
					Unit.PX);
			layoutPanel.setWidgetTopHeight(image_1, 44.0, Unit.PX, 161.0,
					Unit.PX);

			CaptionPanel perfilImagePanel = new CaptionPanel("");
			rootPanel.add(perfilImagePanel, 34, 268);
			perfilImagePanel.setSize("205px", "180px");

			LayoutPanel layoutPanel_1 = new LayoutPanel();
			perfilImagePanel.setContentWidget(layoutPanel_1);
			layoutPanel_1.setSize("193px", "170px");

			Image image = new Image(
					"parceirosDaEstrada.web.ImageViewer/js/ext/resources/images/vista/basic-dialog/avatar.jpeg");
			layoutPanel_1.add(image);
			image.setSize("100", "120");
			layoutPanel_1.setWidgetLeftWidth(image, 21.0, Unit.PX, 164.0,
					Unit.PX);
			layoutPanel_1.setWidgetTopHeight(image, 11.0, Unit.PX, 170.0,
					Unit.PX);

			CaptionPanel captionPanel = new CaptionPanel("");
			rootPanel.add(captionPanel, 34, 458);
			captionPanel.setSize("205px", "363px");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}