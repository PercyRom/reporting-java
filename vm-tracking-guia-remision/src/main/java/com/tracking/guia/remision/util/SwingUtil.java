package com.tracking.guia.remision.util;

import java.awt.Event;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class SwingUtil {

	public static final String TEXTO_FILTRO_COMBO_TODOS = "TODOS";
	public static final String TEXTO_COMBO_VACIO = "----NINGUNO----";
	public static final String TEXTO_COMBO_SMALL_VACIO = "-----";
	public static final String TEXTO_COMBO_SELECCIONE = "[Seleccione]";
	
	public static final void agregarJComponent(JPanel jp,JComponent c,GridBagConstraints gc,int posicionFila,int posicionColumna,
			   int colspan,int rowspan,
			   double pesoEspacioHorizontal,double pesoEspacioVertical,
			   int modoExpansionEnCelda,int posicionEnCelda,
			   Insets margenes){
		if(gc!=null){
			gc.gridx = posicionColumna;
			gc.gridy = posicionFila;
			gc.gridwidth = colspan;
			gc.gridheight = rowspan;
			gc.weighty = pesoEspacioVertical;
			gc.weightx = pesoEspacioHorizontal;
			gc.fill = modoExpansionEnCelda;
			gc.anchor = posicionEnCelda; //Debe pegarse a la izquierda
			gc.insets = margenes;
			jp.add(c,gc);
		}
	}
	
	public static final void bloquearCtrlV(JTextField txtCampo){
		if(txtCampo!=null){
			InputMap mapaEntrada = txtCampo.getInputMap(JTextField.WHEN_FOCUSED);
			mapaEntrada.put(KeyStroke.getKeyStroke(KeyEvent.VK_V,Event.CTRL_MASK), "null");
		}
	}
	
	public static final boolean validarEntradaNumerico(char teclaPulsada){
		boolean valido = true;

		Pattern pattern = Pattern.compile("[0-9]");
		Matcher matcher = null;
		//Dejar pasar si es backspace
		if(teclaPulsada != (char)(8)){
			
			matcher = pattern.matcher(String.valueOf(teclaPulsada));
			if (!matcher.matches()) {
				valido = false;
			}
		}

		return valido;
	}
	
	
	public static final boolean validarEntradaAlfanumerico(char teclaPulsada){
		boolean valido = true;

		Pattern pattern = Pattern.compile("[A-Z a-z 0-9\\-_]");
		Matcher matcher = null;
		//Dejar pasar si es backspace
		if(teclaPulsada != (char)(8)){
			
			matcher = pattern.matcher(String.valueOf(teclaPulsada));
			if (!matcher.matches()) {
				valido = false;
			}
		}

		return valido;
	}
	
	public static final boolean validarEntradaTexto(char teclaPulsada){
		boolean valido = true;

		Pattern pattern = Pattern.compile("[A-Z a-z 0-9\\-_\\.\u00f1\u00d1\\u005c\\u002f\\u007c\\u0028\\u0029\\u005b\\u005d]");
		Matcher matcher = null;
		//Dejar pasar si es backspace
		if(teclaPulsada != (char)(8)){
			
			matcher = pattern.matcher(String.valueOf(teclaPulsada));
			if (!matcher.matches()) {
				valido = false;
			}
		}

		return valido;
	}
	
	public static final boolean validarEntradaDecimalTexto(JTextField campo,char teclaPulsada,int numDecimales){
		boolean valido = true;
		String texto = campo.getText().trim();
		
		//Pattern pattern = Pattern.compile("([0-9])*[.]?[0-9]{0,"+(numDecimales)+"}");
		Pattern pattern = Pattern.compile("([0-9]){0,3}[.]?[0-9]{0,"+(numDecimales)+"}");
		Matcher matcher = null;
		//Dejar pasar si es backspace
		if(teclaPulsada != (char)(8)){
			
			int posicionSeleccionInicial = campo.getSelectionStart();
			int posicionSeleccionFinal = campo.getSelectionEnd();
			
			//Es todo el texto seleccionado
			String strCaracter = String.valueOf(teclaPulsada);
			if(posicionSeleccionInicial==0 && 
			   posicionSeleccionFinal==texto.length()){
				matcher = pattern.matcher(strCaracter);
			}else{
				//Es solo una seleccion del texto
				if(posicionSeleccionInicial!=posicionSeleccionFinal){
					String textoSeleccionado = texto.substring(campo.getSelectionStart(),campo.getSelectionEnd());
					String textoSinSeleccionar = texto.replace(textoSeleccionado,strCaracter);
					matcher = pattern.matcher(textoSinSeleccionar);
				}else{
					int posicionCursor = campo.getCaretPosition();
					if(posicionCursor == texto.length()){
						matcher = pattern.matcher(texto+strCaracter);
					}else{
						String textoAnterior = texto.substring(0,posicionCursor);
						String textoPosterior = texto.replace(textoAnterior,"");
						matcher = pattern.matcher(textoAnterior+strCaracter+textoPosterior);
					}	
				}
			}
			
			if (!matcher.matches()) {
				valido = false;
			}
		}
		
		return valido;
	}
}