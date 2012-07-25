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

import componentesdosistema.Usuario;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.TextBox;



public class PainelLocalizarParceiros  implements Painel {

	/**
	 * @wbp.parser.entryPoint
	 */
	public LayoutPanel carregaPainel(){
		LayoutPanel usuariosPanel = new LayoutPanel();
		usuariosPanel.setSize("760px", "542px");

		final CellTable<Usuario> cellTable = new CellTable<Usuario>();
		cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		usuariosPanel.add(cellTable);
		usuariosPanel.setWidgetLeftWidth(cellTable, 0.0, Unit.PX, 737.0, Unit.PX);
		usuariosPanel.setWidgetTopHeight(cellTable, 94.0, Unit.PX, 433.0, Unit.PX);
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


		final TextBox textBox_3 = new TextBox();
		usuariosPanel.add(textBox_3);
		usuariosPanel.setWidgetLeftWidth(textBox_3, 114.0, Unit.PX, 323.0, Unit.PX);
		usuariosPanel.setWidgetTopHeight(textBox_3, 11.0, Unit.PX, 36.0, Unit.PX);

		Button button = new Button("Buscar Usuario");
		usuariosPanel.add(button);
		usuariosPanel.setWidgetLeftWidth(button, 447.0, Unit.PX, 191.0, Unit.PX);
		usuariosPanel.setWidgetTopHeight(button, 11.0, Unit.PX, 36.0, Unit.PX);
		//final ControleDeAcessoAsync controleAcesso = GWT.create(ControleDeAcesso.class);

		
		button.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				try {
					List<Usuario> todosUsuarios = SistemaWebMain.getSistema().getUsuarios();
					List<Usuario> usuaiosEncontrados = new ArrayList<Usuario>();
					for (Usuario user: todosUsuarios){
						if (user.getNome().toLowerCase().contains(textBox_3.getText().toLowerCase()))
							usuaiosEncontrados.add(user);
					}
					cellTable.setRowCount(usuaiosEncontrados.size(), true);
					cellTable.setRowData(0, usuaiosEncontrados);
				} catch (Exception e) {
					Window.alert(e.getMessage() + "..!");
				}
			}
		});
		return usuariosPanel;
	}

}
