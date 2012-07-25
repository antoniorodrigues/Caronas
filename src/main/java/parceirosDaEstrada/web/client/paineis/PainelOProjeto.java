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

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;


public class PainelOProjeto  implements Painel{
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public LayoutPanel carregaPainel(){
		LayoutPanel layoutOProjeto = new LayoutPanel();
		layoutOProjeto.setSize("760px", "542px");

		Label lblNewLabel = new Label(
				"Parceiros Da Estrada");
		lblNewLabel.setStyleName("x-grid3-header-pop");
		layoutOProjeto.add(lblNewLabel);
		layoutOProjeto.setWidgetLeftWidth(lblNewLabel, 78.0, Unit.PX, 421.0,
				Unit.PX);
		layoutOProjeto.setWidgetTopHeight(lblNewLabel, 17.0, Unit.PX, 37.0,
				Unit.PX);

		Label lblProjetoDa_1 = new Label("- Projeto da Disciplina de Sistemas de Informa\u00E7\u00E3o-\r\n");
		lblProjetoDa_1.setStyleName("x-grid3-header-offset");
		layoutOProjeto.add(lblProjetoDa_1);		
		layoutOProjeto.setWidgetLeftWidth(lblProjetoDa_1, 78.0, Unit.PX, 3072.0, Unit.PX);
		layoutOProjeto.setWidgetTopHeight(lblProjetoDa_1, 104.0, Unit.PX, 37.0, Unit.PX);

		Label label_1 = new Label("-Desenvolvedores:");
		label_1.setStyleName("x-grid3-header-offset");
		layoutOProjeto.add(label_1);
		label_1.setSize("200px", "50px");
		layoutOProjeto.setWidgetLeftWidth(label_1, 58.0, Unit.PX, 301.0, Unit.PX);
		layoutOProjeto.setWidgetTopHeight(label_1, 92.0, Unit.PX, 23.0, Unit.PX);

		Label label_2 = new Label("-Descri\u00E7\u00E3o:");
		label_2.setStyleName("x-grid3-header-offset");
		layoutOProjeto.add(label_2);
		label_2.setSize("100px", "50px");
		layoutOProjeto.setWidgetLeftWidth(label_2, 84.0, Unit.PX, 301.0, Unit.PX);
		layoutOProjeto.setWidgetTopHeight(label_2, 231.0, Unit.PX, 50.0, Unit.PX);

		Label lblProjetoDa = new Label("O sistema consiste em uma rede social na web para compartilhamento de caronas.\r\n\tOs usu\u00E1rios solicitam ou divulgam uma carona, e o sistema ajuda a pare\u00E1-los.\r\n\tO sistema facilitar\u00E1 que motoristas e caroneiros encontrem as melhores op\u00E7\u00F5es\r\n\tpara uma carona, e ajudar\u00E1 na negocia\u00E7\u00E3o envolvida em solicitar uma carona, \r\n\tdo ponto de vista dos caroneiros, e em escolher os melhores caroneiros, do \r\n\tponto de vista dos motoristas.\r\n\tMelhores caroneiros podem ser definidos por v\u00E1rios crit\u00E9rios, como local de\r\n\tresid\u00EAncia, reputa\u00E7\u00E3o, ou conhecimento pr\u00E9vio.");
		layoutOProjeto.add(lblProjetoDa);
		layoutOProjeto.setWidgetLeftWidth(lblProjetoDa, 123.0, Unit.PX, 421.0, Unit.PX);
		layoutOProjeto.setWidgetTopHeight(lblProjetoDa, 266.0, Unit.PX, 171.0, Unit.PX);

		Label label = new Label("Antonio Marcos, Diego Augusto, Eduardo Rovaris, Laercio Virturino e Rodolfo Rocha");
		layoutOProjeto.add(label);
		label.setSize("320px", "30px");
		layoutOProjeto.setWidgetLeftWidth(label, 123.0, Unit.PX, 428.0, Unit.PX);
		layoutOProjeto.setWidgetTopHeight(label, 121.0, Unit.PX, 30.0, Unit.PX);

		Label label_3 = new Label("1.2.0");
		layoutOProjeto.add(label_3);
		label_3.setSize("320px", "30px");
		layoutOProjeto.setWidgetLeftWidth(label_3, 121.0, Unit.PX, 320.0, Unit.PX);
		layoutOProjeto.setWidgetTopHeight(label_3, 192.0, Unit.PX, 30.0, Unit.PX);

		Label label_4 = new Label("-Vers\u00E3o:");
		label_4.setStyleName("x-grid3-header-offset");
		layoutOProjeto.add(label_4);
		label_4.setSize("80px", "50px");
		layoutOProjeto.setWidgetLeftWidth(label_4, 81.0, Unit.PX, 301.0, Unit.PX);
		layoutOProjeto.setWidgetTopHeight(label_4, 160.0, Unit.PX, 50.0, Unit.PX);

		Label label_5 = new Label("Universidade Federal de Campina Grande - 2012");
		label_5.setStyleName("x-grid3-header-offset");
		layoutOProjeto.add(label_5);
		label_5.setSize("300px", "50px");
		layoutOProjeto.setWidgetLeftWidth(label_5, 198.0, Unit.PX, 301.0, Unit.PX);
		layoutOProjeto.setWidgetTopHeight(label_5, 470.0, Unit.PX, 50.0, Unit.PX);

		Label label_6 = new Label("Projeto da Disciplina Sistemas de Informa\u00E7\u00E3o");
		label_6.setStyleName("x-grid3-header-offset");
		layoutOProjeto.add(label_6);
		label_6.setSize("350px", "50px");
		layoutOProjeto.setWidgetLeftWidth(label_6, 59.0, Unit.PX, 351.0, Unit.PX);
		layoutOProjeto.setWidgetTopHeight(label_6, 42.0, Unit.PX, 50.0, Unit.PX);

		return layoutOProjeto;
	}

}
