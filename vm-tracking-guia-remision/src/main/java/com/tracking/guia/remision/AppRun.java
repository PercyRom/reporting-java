package com.tracking.guia.remision;

import javax.swing.UIManager;

import com.tracking.guia.remision.frm.PanelControl;

public class AppRun {

	public static PanelControl panelControl;

	public static PanelControl getPanelControl() {
		return panelControl;
	}

	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		panelControl = new PanelControl();
		panelControl.setVisible(true);
		panelControl.fnIniciar();
	}
}