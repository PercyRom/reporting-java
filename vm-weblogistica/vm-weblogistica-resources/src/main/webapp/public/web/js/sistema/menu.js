var opcionesMenu = [];

function fnInicializarMenu(rutaRaiz,opcionesMenuStringJSON){
	opcionesMenu = opcionesMenuStringJSON;
	
	var cadenaMenu = "";
	var i = 0;
	var j = 0;
	var dim = opcionesMenu.length;
	
	//Listar las opciones padre
	var opcionesPrincipales = [];
	var opcionesItems = [];
	while(i<dim){
		var objJSON = opcionesMenu[i];
		
		if(objJSON["esMenu"] == "N" || objJSON["idPadre"] == 0){
			opcionesPrincipales.push(objJSON);
		}else{
			opcionesItems.push(objJSON);
		}
		
		i = i + 1;
	}
	
	i = 0;
	dim = opcionesPrincipales.length;

//	console.log(opcionesPrincipales);
	while(i<dim){
		var objJSON = opcionesPrincipales[i];
		
		if(objJSON["esMenu"] == "S" && objJSON["idPadre"] == 0){
			cadenaMenu = cadenaMenu + '<li class="nav-item"><a class="nav-link" href="#" onclick="fnCargarOpcion(\''+rutaRaiz+'/'+objJSON["linkOpcion"]+'\')"><i class="fas fa-fw '+objJSON["icono"]+'"></i><span>&nbsp;'+objJSON["nombreOpcion"]+'</span></a></li>';
		}else{
			//Armar la cabecera del folder
			var idFolder = "pagesDropdown_"+i;

			cadenaMenu = cadenaMenu + '<li class="nav-item dropdown">';
			cadenaMenu = cadenaMenu + 
							'<a class="nav-link dropdown-toggle" href="#" id="'+idFolder+'" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">'+
							'<i class="fas fa-fw fa-folder"></i><span>&nbsp;'+objJSON["nombreOpcion"]+'</span></a>';
			
			//Armar la base del folder
			cadenaMenu = cadenaMenu + '<div class="dropdown-menu" aria-labelledby="'+idFolder+'">';
			//cadenaMenu = cadenaMenu + '<h6 class="dropdown-header">Opciones:</h6>';

			//Listar los elementos items
			j = 0;
			var listaTemporalItems = [];
			while(j < opcionesItems.length){
				var objItem = opcionesItems[j];
				if(objItem["idPadre"] == objJSON["idOpcion"]){
					listaTemporalItems.push(objItem);
				}
				j = j + 1;
			}
			
			var k = 0;
			while(k < listaTemporalItems.length){

				var objItem = listaTemporalItems[k];
				cadenaMenu = cadenaMenu + '<a href="#" class="dropdown-item" onclick="fnCargarOpcion(\''+rutaRaiz+'/'+objItem["linkOpcion"]+'\')">'+objItem["nombreOpcion"]+'</a>';

				k = k + 1;
				if(k < listaTemporalItems.length){
					cadenaMenu = cadenaMenu + '<div class="dropdown-divider"></div>';
				}
			}

			cadenaMenu = cadenaMenu + '</div></li>';
		}

		i = i + 1;
	}

//	console.log(cadenaMenu);
	$("#listadoMenu").append(cadenaMenu);
}

function fnCargarOpcion(url){
	$(location).attr('href',url);
}