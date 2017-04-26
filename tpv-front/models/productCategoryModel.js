tpv.service("prodCatModel", function(){
	var catProd=[{
		id:1,
		name:'platano',
		description:'amarillo'
	},{
		id:2,
		name:'manzana',
		description:'roja'
	}];

	this.save=function(prod){
		if(prod==null){
			alert('nulo');
		}else{
			var mayor=-1;
			var prodCatArr=eval(catProd);
			for(var i=0; i<prodCatArr.length; i++){
				if(mayor<prodCatArr[i].id){
					mayor=prodCatArr[i].id;
				}
			}
			mayor=mayor+1;

			catProd.push({
				id:mayor,
				name:prod.name,
				description:prod.description
			});
		}
	}

	this.get=function(data){

	}

	this.remove=function(index){
		var pos=-1;
		var prodCatArr=eval(catProd);
		for(var i=0;i<prodCatArr.length;i++){
			if(prodCatArr[i].id==index){
				pos=i;
				break;
			}
		}

		if(pos==-1){
			alert("Something gone wrong");
		}
		catProd.splice(pos,1);
	}

	this.update=function(index, catProdItem){
		var pos=-1;
		var prodCatArr=eval(catProd);
		for(var i=0; i<prodCatArr.length;i++){
			if(prodCatArr[i].id==index){
				pos=i;
				break;
			}
		}
		if(pos==-1){
			alert("Something gone wrong");
		}
		catProd[pos].name=catProdItem.name;
		catProd[pos].description=catProdItem.description
	}

	this.list=function(){
		return catProd;
	}

	/*this.getProduct=function(){
		return[{
			id: 1,
			name:"Lacteos",
			description:"leche, crema comestible"
		},{
			id:2,
			name:"Frutas",
			descripcion:"manzana, platano, fresa, entre otros"
		},{
			id:3,
			name:"Verduras",
			descripcion:"chayote, perejil, espinacas"
		},{
			id:4,
			name:"Carnes",
			descripcion:"puerco, res"
		},{
			id:5,
			name:"pescado",
			descripcion:"mojarra, camarones, pulpo"
		},{
			id:6,
			name:"Bebidas",
			descripcion:"vino, cerveza, refrescos"
		}
		]

	}*/
});