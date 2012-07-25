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

import sistema.SistemaWebMain;

import componentesdosistema.SolicitacaoDeCarona;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;



public class PainelInicial implements Painel {
	private String login;
	private LayoutPanel layoutLocalizarCaronas;
	private CellTable<SolicitacaoDeCarona> table;
	private List<SolicitacaoDeCarona> solicitacoes;

	public PainelInicial(String login) {
		this.login = login;
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public LayoutPanel carregaPainel() {
		LayoutPanel painelPerfil = new LayoutPanel();
		try{
			painelPerfil.setSize("754px", "549px");

			Label lblNewLabel_1 = new Label("Bem Vindo, " + login);
			painelPerfil.add(lblNewLabel_1);
			painelPerfil.setWidgetLeftWidth(lblNewLabel_1, 299.0, Unit.PX, 173.0, Unit.PX);
			painelPerfil.setWidgetTopHeight(lblNewLabel_1, 0.0, Unit.PX, 26.0, Unit.PX);

			Label label = new Label("Nome: " + SistemaWebMain.getSistema().getAtributoPerfil(login, "nome"));

			painelPerfil.add(label);
			label.setWidth("330px");
			painelPerfil.setWidgetLeftWidth(label, 41.0, Unit.PX, 173.0, Unit.PX);
			painelPerfil.setWidgetTopHeight(label, 63.0, Unit.PX, 26.0, Unit.PX);

			Label label_1 = new Label("Email: " + SistemaWebMain.getSistema().getAtributoPerfil(login, "email"));
			painelPerfil.add(label_1);
			label_1.setWidth("330px");
			painelPerfil.setWidgetRightWidth(label_1, 383.0, Unit.PX, 330.0, Unit.PX);
			painelPerfil.setWidgetTopHeight(label_1, 89.0, Unit.PX, 19.0, Unit.PX);

			Label label_2 = new Label("Endere\u00E7o: " + SistemaWebMain.getSistema().getAtributoPerfil(login, "endereco"));
			painelPerfil.add(label_2);
			label_2.setWidth("330px");
			painelPerfil.setWidgetLeftWidth(label_2, 41.0, Unit.PX, 173.0, Unit.PX);
			painelPerfil.setWidgetTopHeight(label_2, 114.0, Unit.PX, 65.0, Unit.PX);

			Label label_3 = new Label("Faltas em Caronas: " + SistemaWebMain.getSistema().getAtributoPerfil(login, "faltas em vagas de caronas"));
			painelPerfil.add(label_3);
			painelPerfil.setWidgetLeftWidth(label_3, 525.0, Unit.PX, 173.0, Unit.PX);
			painelPerfil.setWidgetTopHeight(label_3, 44.0, Unit.PX, 26.0, Unit.PX);

			Label label_4 = new Label("Presen\u00E7a em Caronas: " + SistemaWebMain.getSistema().getAtributoPerfil(login, "presen�as em vagas de caronas"));
			painelPerfil.add(label_4);
			painelPerfil.setWidgetRightWidth(label_4, 56.0, Unit.PX, 173.0, Unit.PX);
			painelPerfil.setWidgetTopHeight(label_4, 73.0, Unit.PX, 26.0, Unit.PX);

			Label label_5 = new Label("Numero de Caronas Legais: " + SistemaWebMain.getSistema().getAtributoPerfil(login, "caronas seguras e tranquilas"));
			painelPerfil.add(label_5);
			painelPerfil.setWidgetRightWidth(label_5, 56.0, Unit.PX, 173.0, Unit.PX);
			painelPerfil.setWidgetTopHeight(label_5, 107.0, Unit.PX, 26.0, Unit.PX);

			Label label_6 = new Label("Numero de Caronas Ruins: " + SistemaWebMain.getSistema().getAtributoPerfil(login, "caronas que n�o funcionaram"));
			painelPerfil.add(label_6);
			painelPerfil.setWidgetRightWidth(label_6, 56.0, Unit.PX, 173.0, Unit.PX);
			painelPerfil.setWidgetTopHeight(label_6, 143.0, Unit.PX, 26.0, Unit.PX);


			CaptionPanel cptnpnlNewPanel = new CaptionPanel("Meus Dados");
			painelPerfil.add(cptnpnlNewPanel);
			painelPerfil.setWidgetLeftWidth(cptnpnlNewPanel, 13.0, Unit.PX, 725.0, Unit.PX);
			painelPerfil.setWidgetTopHeight(cptnpnlNewPanel, 30.0, Unit.PX, 152.0, Unit.PX);

			CaptionPanel captionPanel = new CaptionPanel("Minhas Caronas");
			painelPerfil.add(captionPanel);
			painelPerfil.setWidgetLeftWidth(captionPanel, 13.0, Unit.PX, 725.0, Unit.PX);
			painelPerfil.setWidgetTopHeight(captionPanel, 185.0, Unit.PX, 165.0, Unit.PX);

			CaptionPanel captionPanel_1 = new CaptionPanel("Solicita\u00E7\u00F5es Pendentes");
			painelPerfil.add(captionPanel_1);
			painelPerfil.setWidgetLeftWidth(captionPanel_1, 13.0, Unit.PX, 725.0, Unit.PX);
			painelPerfil.setWidgetTopHeight(captionPanel_1, 356.0, Unit.PX, 180.0, Unit.PX);

			LayoutPanel minhasCaronas = new LayoutPanel();
			minhasCaronas.add(carregaMinhasSolicitacoes());
			minhasCaronas.setWidgetLeftWidth(layoutLocalizarCaronas, 0.0, Unit.PX, 697.0, Unit.PX);
			minhasCaronas.setWidgetTopHeight(layoutLocalizarCaronas, -12.0, Unit.PX, 174.0, Unit.PX);
			painelPerfil.add(minhasCaronas);
			painelPerfil.setWidgetLeftWidth(minhasCaronas, 26.0, Unit.PX, 697.0, Unit.PX);
			painelPerfil.setWidgetTopBottom(minhasCaronas, 371.0, Unit.PX, 13.0, Unit.PX);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return painelPerfil;
	}

	public LayoutPanel carregaMinhasSolicitacoes(){
		solicitacoes = new ArrayList<SolicitacaoDeCarona>();
		try {
			solicitacoes = SistemaWebMain.getSistema().getSolicitacoesPendentes(login);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		layoutLocalizarCaronas = new LayoutPanel();

		ScrollPanel scrollPanel = new ScrollPanel();
		layoutLocalizarCaronas.add(scrollPanel);
		layoutLocalizarCaronas.setWidgetLeftWidth(scrollPanel, 0.0, Unit.PX,
				680.0, Unit.PX);
		layoutLocalizarCaronas.setWidgetTopBottom(scrollPanel, 15.0, Unit.PX,
				4.0, Unit.PX);
		scrollPanel.setSize("680px", "155px");

		// Cria a CellTable que mostra a lista de caronas ao usuario
		table = new CellTable<SolicitacaoDeCarona>();
		scrollPanel.setWidget(table);
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		TextColumn<SolicitacaoDeCarona> destinoColumn = new TextColumn<SolicitacaoDeCarona>() {
			@Override
			public String getValue(SolicitacaoDeCarona object) {
				return object.getDonoDaSolicitacao().getNome();
			}
		};
		table.addColumn(destinoColumn, "Dono da Solicita\u00E7\u00E3o");

		TextColumn<SolicitacaoDeCarona> origemColumn = new TextColumn<SolicitacaoDeCarona>() {
			@Override
			public String getValue(SolicitacaoDeCarona solicitacao) {
				return "solicitou vaga na carona: "+solicitacao.getCarona().getTrajeto();
			}
		};

		table.addColumn(origemColumn, "Carona");

		Column<SolicitacaoDeCarona, String> column = new Column<SolicitacaoDeCarona, String>(new ButtonCell()) {
			@Override
			public void onBrowserEvent(Context context, Element elem,
					SolicitacaoDeCarona object, NativeEvent event) {
				try {
					SistemaWebMain.getSistema().aceitarSolicitacao(object.getDonoDaCarona().getID(), object.getIdSolcitacao());			
					Window.alert("A solicita\u00E7\u00E3o do parceiro "+ object.getDonoDaSolicitacao().getNome() + " foi aceita!");
					solicitacoes = SistemaWebMain.getSistema().getSolicitacoesPendentes(login);
					table.setRowCount(solicitacoes.size(), true);
					table.setRowData(solicitacoes);

				} catch (Exception e) {
					Window.alert(e.getMessage());
					e.printStackTrace();
				}
				super.onBrowserEvent(context, elem, object, event);
			}
			@Override
			public String getValue(SolicitacaoDeCarona object) {
				return "Aceitar Solicita\u00E7\u00E3o";
			}
		};


		table.addColumn(column, "O que deseja fazer?");
		Column<SolicitacaoDeCarona, String> column2 = new Column<SolicitacaoDeCarona, String>(new ButtonCell()) {
			@Override
			public void onBrowserEvent(Context context, Element elem,
					SolicitacaoDeCarona object, NativeEvent event) {
				try {
					SistemaWebMain.getSistema().rejeitarSolicitacao(object.getDonoDaCarona().getID(), object.getIdSolcitacao());			
					Window.alert("Voce acaba de rejeitar a solicitacao do parceiro "+ object.getDonoDaSolicitacao().getNome() + " nessa carona!");
					solicitacoes = SistemaWebMain.getSistema().getSolicitacoesPendentes(login);
					table.setRowCount(solicitacoes.size(), true);
					table.setRowData(solicitacoes);

				} catch (Exception e) {
					Window.alert(e.getMessage());
					e.printStackTrace();
				}
				super.onBrowserEvent(context, elem, object, event);
			}
			@Override
			public String getValue(SolicitacaoDeCarona object) {
				return "Recusar Solicita\u00E7\u00E3o";
			}
		};
		table.addColumn(column2, "");

		table.setRowCount(solicitacoes.size(), true);
		table.setRowData(solicitacoes);
		table.setSize("688px", "239px");
		if (solicitacoes.size() > 0)
			Window.alert("Ola " + login + ", voce tem ("+solicitacoes.size() + ") novas solicitacoes pendentes!");
		return layoutLocalizarCaronas;

	}
}