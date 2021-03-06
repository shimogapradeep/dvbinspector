/**
 *
 *  http://www.digitalekabeltelevisie.nl/dvb_inspector
 *
 *  This code is Copyright 2009-2018 by Eric Berendsen (e_berendsen@digitalekabeltelevisie.nl)
 *
 *  This file is part of DVB Inspector.
 *
 *  DVB Inspector is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  DVB Inspector is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with DVB Inspector.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  The author requests that he be notified of any application, applet, or
 *  other binary that makes use of this code, but that's more out of curiosity
 *  than anything and is not required.
 *
 */

package nl.digitalekabeltelevisie.gui;

import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import nl.digitalekabeltelevisie.data.mpeg.TransportStream;
import nl.digitalekabeltelevisie.main.DVBinspector;

public abstract class AbstractSetPreferenceAction extends AbstractAction {

    protected DVBinspector contr;

    static final Logger logger = Logger.getLogger(AbstractSetPreferenceAction.class.getName());

	public AbstractSetPreferenceAction(DVBinspector controller) {
		contr = controller;
	}

	public AbstractSetPreferenceAction(DVBinspector controller, String label) {
		super(label);
		contr = controller;
	}

    /**
     * 
     */
    protected void askReloadStream() {
	final TransportStream ts = contr.getTransportStream();
	if (ts != null) {
	    Object[] options = { "Yes, reload stream (may take some time)",
		    "No, setting only takes effect after next load" };
	    int n = JOptionPane.showOptionDialog(contr.getFrame(),
		    "For this option to take effect the stream has to be reloaded.\n "
			    + "Do you want to reload the stream now?",
		    "reload stream?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
		    options[1]); // default to no
		    if (n == 0) {
		    	final TSLoader tsLoader = new TSLoader(ts.getFile(), contr);
		    	tsLoader.execute();
		    }
		}
    }

}