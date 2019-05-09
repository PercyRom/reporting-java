package com.tracking.guia.remision.frm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory;
import com.tracking.guia.remision.util.Constantes;
import com.tracking.guia.remision.util.SwingUtil;
import com.tracking.guia.remision.util.Util;

public class PanelControl extends JFrame {

	private static final long serialVersionUID = 1L;

	public PanelControl() {
		super();

		Dimension d = new Dimension();
		d.setSize(new Dimension(500, 400));

		ImageIcon icono = new ImageIcon(getClass().getResource("/logo-vendemas.jpg"));
		this.setIconImage(icono.getImage());

		tamanioFuenteEtiquetas = 11;
		colorBordes = new Color(213, 223, 229);
		tipoLetra = "Tahoma";
		fuenteTitulo = new Font(tipoLetra, Font.BOLD, tamanioFuenteEtiquetas);
		fuenteEtiqueta = new Font(tipoLetra, Font.PLAIN, tamanioFuenteEtiquetas);
		fuenteCombos = new Font(tipoLetra, Font.PLAIN, tamanioFuenteEtiquetas);
		fuenteBotones = new Font(tipoLetra, Font.PLAIN, tamanioFuenteEtiquetas);

		panelContenedor = new JPanel(new GridBagLayout());
		panelContenedor.setBackground(Color.WHITE);

		paddingVentana = 6;

		fnInicializarPanelDatos();
		fnCargarImpresoras();

		GridBagConstraints gc = new GridBagConstraints();
		SwingUtil.agregarJComponent(panelContenedor, panelDatos, gc, 0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.BOTH,
				GridBagConstraints.NORTHWEST,
				new Insets(paddingVentana, paddingVentana, paddingVentana, paddingVentana));
		setContentPane(panelContenedor);

		this.setBackground(Color.WHITE);
		this.setTitle("Gestor Impresiones Tracking");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.pack();
		this.setSize(d);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				fnCerrarAplicacion();
			}
		});

	}

	public void fnCerrarAplicacion() {

	}

	public void fnIniciar() {

		String host = Util.getPropiedad(Constantes.SERVICE_PRINT_WS_HOST);
		String port = Util.getPropiedad(Constantes.SERVICE_PRINT_WS_PORT);

		final String uri = "http://" + host + ":" + port + "/";
		final Map<String, String> initParams = new HashMap<String, String>();

		initParams.put("com.sun.jersey.config.property.packages", "com.tracking.guia.remision.ws");
		
		try {
			setSelector(GrizzlyWebContainerFactory.create(uri, initParams));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void fnCargarConsola(String decode) {
		txtConsola.setText(decode);
	}

	private void fnCargarImpresoras() {
		PrintService printServiceDefault = PrintServiceLookup.lookupDefaultPrintService();
		PrintService[] ps = PrintServiceLookup.lookupPrintServices(null, null);

		if (ps.length > 0) {
			cbxSelectorImpresoras.removeAllItems();
			for (int i = 0; i < ps.length; i++) {
				cbxSelectorImpresoras.addItem(ps[i].getName());
			}

			cbxSelectorImpresoras.setSelectedItem(printServiceDefault.getName());
		}
	}
	
	public PrintService fnImpresora() {
		PrintService printer = null;
		PrintService[] printers = PrintServiceLookup.lookupPrintServices(null, null);
		String selected = cbxSelectorImpresoras.getSelectedItem().toString();

		for (int i = 0; i < printers.length; i++) {
			if (printers[i].getName().compareTo(selected) == 0) {
				printer = printers[i];
			}
		}
		return printer;
	}

	private void fnInicializarPanelDatos() {

		panelDatos = new JPanel(new GridBagLayout());
		panelDatos.setBackground(Color.WHITE);

		TitledBorder bordeDatosTransporte = BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(colorBordes), "Datos de impresi\u00f3n",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, fuenteTitulo);

		panelDatos.setBorder(bordeDatosTransporte);

		lblSelectorImpresoras = new JLabel("Impresora : ");
		lblSelectorImpresoras.setFont(fuenteEtiqueta);
		lblSelectorImpresoras.setHorizontalTextPosition(JLabel.RIGHT);

		cbxSelectorImpresoras = new JComboBox<Object>();
		cbxSelectorImpresoras.setFont(fuenteCombos);
		cbxSelectorImpresoras.addItem(SwingUtil.TEXTO_COMBO_VACIO);

		btnLimpiarConsola = new JButton("Limpiar");
		btnLimpiarConsola.setFont(fuenteBotones);
		btnLimpiarConsola.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				txtConsola.setText("");
			}
		});

		txtConsola = new JTextArea();
		txtConsola.setEditable(false);
		txtConsola.setBackground(Color.BLACK);
		txtConsola.setForeground(Color.WHITE);
		scrollTxtConsola = new JScrollPane(txtConsola);
		scrollTxtConsola.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		margenesInputLabel = new Insets(0, 2, 2, 2);

		// Agregar componentes
		GridBagConstraints gc = new GridBagConstraints();

		// FILA 1
		Integer numFila = 0;
		Integer columna = 0;
		double porcentajeFila = 0.2;
		SwingUtil.agregarJComponent(panelDatos, lblSelectorImpresoras, gc, numFila, columna, 1, 1, 0.2, porcentajeFila,
				GridBagConstraints.NONE, GridBagConstraints.EAST, margenesInputLabel);
		columna++;
		SwingUtil.agregarJComponent(panelDatos, cbxSelectorImpresoras, gc, numFila, columna, 1, 1, 0.5, porcentajeFila,
				GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, margenesInputLabel);
		columna++;
		SwingUtil.agregarJComponent(panelDatos, btnLimpiarConsola, gc, numFila, columna, 1, 1, 0.3, porcentajeFila,
				GridBagConstraints.NONE, GridBagConstraints.WEST, margenesInputLabel);
		columna++;

		numFila = numFila + 1;
		columna = 0;
		porcentajeFila = 0.8;
		SwingUtil.agregarJComponent(panelDatos, scrollTxtConsola, gc, numFila, columna, 3, 1, 0.8, porcentajeFila,
				GridBagConstraints.BOTH, GridBagConstraints.NORTHWEST, margenesInputLabel);
		columna++;
	}

	private Integer tamanioFuenteEtiquetas;
	private Color colorBordes;
	private String tipoLetra;
	private JPanel panelDatos;
	private Font fuenteEtiqueta;
	private Font fuenteTitulo;
	private Font fuenteCombos;
	private Font fuenteBotones;
	private JLabel lblSelectorImpresoras;
	private JComboBox<Object> cbxSelectorImpresoras;
	private JButton btnLimpiarConsola;
	private JTextArea txtConsola;
	private JScrollPane scrollTxtConsola;
	private Insets margenesInputLabel;
	private JPanel panelContenedor;
	private Integer paddingVentana;
	private SelectorThread selector;

	public JTextArea getTxtConsola() {
		return txtConsola;
	}

	public void setTxtConsola(JTextArea txtConsola) {
		this.txtConsola = txtConsola;
	}

	public SelectorThread getSelector() {
		return selector;
	}

	public void setSelector(SelectorThread selector) {
		this.selector = selector;
	}

}
