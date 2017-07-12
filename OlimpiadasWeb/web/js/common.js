/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 function siguienteHora(fecha,hora){
    if ( fecha === "" ) fecha = moment().format('DD/MM/YYYY');
    if ( hora === "" ) hora = moment().subtract(1,'hour').format('HH:mm:ss');  // Tomamos una hora antes

    var m = moment(fecha + " " + hora,'DD/MM/YYYY HH:mm:ss');

    if (m.isValid()){
        m.add(1,'hour');
        return [m.format("DD/MM/YYYY"),m.format("HH:mm:ss")];
    }
    else return ["",""];
 }
 function wrapTag(tag,value,cls,id){
             var str = "<"   + tag;
             if (cls!=undefined) {str += " class='" + cls+"'"};
             if(id!=undefined){ str += " id= '"+ id +"'";}
             str += ">";
             str += value
             str += "</" + tag+">";
             return str;
         }
function convertirFecha(fecha) { 
    if(!fecha) return "";
    return moment(fecha,'YYYY-MM-DD').format("DD/MM/YYYY");
}
function convertirFechayHora(fecha){
    return moment(fecha,'YYYY-MM-DD hh:mm:ss').format("DD/MM/YYYY hh:mm:ss");
}
function validateDate(md_date,md_time){
    var newDate = moment(md_date + " " + md_time,'DD/MM/YYYY HH:mm:ss');
    return newDate.isValid();
}
function validarFecha(fecha,formato){
    if (formato===undefined) formato = "DD/MM/YYYY";
    return moment(fecha,formato,true).isValid();
}
function availableDate(md_date,md_time,rows){
    if (rows === undefined)
        var rows = $('#measured_data tbody tr').not('.data-unsaved');
    var newDate = moment(md_date + " " + md_time,'DD/MM/YYYY HH:mm:ss');
    
    var arrFechas = [];
    for(var i = 0;i<= rows.length;i++){
        var row = $(rows[i]);
        var fecha = row.find('.data-date').text();
        var hora = row.find('.data-time').text();
        var m = moment(fecha + " " + hora, "DD/MM/YYYY HH:mm:ss");              
        if (m.isValid()){                  
            arrFechas.push(m);              
       }
     }

    for ( var i = 0;i<arrFechas.length;i++){
      if (newDate.isSame(arrFechas[i])){          
          return false;
      };
    }
    return true;
}
function movePage(side){
    if (side === "left") {
        if(page>0) page--;
    } else {
        page++;
    }            
    data.pagNro = page;
    if(page==0){$('.data-left').css('visibility','hidden' )}
    if(page>0) {$('.data-left').css('visibility','visible')}
    loadData(data);
}
function exportar(tipo,formato){
    var data = {};
    data.type=tipo;
    data.format = formato;
    var location = "Excel?";
    for (d in data){
        location += d + "=" + data[d] + "&";
    }
    window.location = location;
}
 function filtrar(e){
    if ($dia_desde.val()!==""){
        data.dia_desde = $dia_desde.val();
    } else { delete data.dia_desde;}
    if ($hora_desde.val()!==""){
        data.hora_desde = $hora_desde.val();
    } else { delete data.hora_desde;}
    if ($dia_hasta.val()!==""){
        data.dia_hasta = $dia_hasta.val();
    } else {delete data.dia_hasta;}
    if ($hora_hasta.val()) data.hora_hasta = $hora_hasta.val();
    else {delete data.hora_hasta;}
    loadData(data);
}
function clearFilter(e){
    $dia_desde.val("");    
    $hora_desde.val("");
    $dia_hasta.val("");
    $hora_hasta.val("");
    delete data.dia_desde;
    delete data.hora_desde;
    delete data.dia_hasta;
    delete data.hora_hasta;
    loadData(data);
}

 function deleteData(url,data,onSuccess){
    //$tr = $(this).parent().parent();
    bootbox.confirm("Esta seguro que desea eliminar el registro?",function(result){
         if(result) {
             $.ajax({
                url: url, //DATAENTRY_DEL
                data: data,
                method:"POST",
                dataType: "json",
                success: onSuccess
             });

         }
     });
}

function searchData(url,data,onSuccess){
    $.ajax({
       url: url,
       data: data,
       method:"POST",
       dataType: "json",
       success: onSuccess
});
}

function rubroChange(url,data,$id_subrubro) {           
    if($id_subrubro===undefined) $id_subrubro =  $('#id_subrubro');
    
    $id_subrubro.html("");
    
    if (data.id_rubro!==0) {
        $.ajax({
            url: url,
            data: data ,
            method:"POST",
            dataType: "json",
            success: function(result) {
                var html = "";
                
                if(result.Result==="OK"){
                    $id_subrubro.html(createSelect(result.Records));
                }
            }
        });
    }
    if (window.loadDataActivo) loadDataActivo(data);
    if (window.loadDataKit)    loadDataKit(data);
}
function subrubroChange() {                       
    $id_rubro = $('#id_rubro');
    loadDataActivo({id_subrubro:$(this).val(),
              id_rubro:$id_rubro.val() 
    });
}

function createSelect(data){
    var html = "<option value='0'>Todos</option>";
    for (var i=0;i<data.length;i++){
        d = data[i];                
        html += "<option value='" + d.id + "'>" + d.descripcion+ "</option>";
    }
    return html;
}

function submitForm(e){
    e.preventDefault();                
    if(validar()){
        $('form').submit();
    }
}

function selTodos(filtro,checked){
    var $arr = $(filtro);
    for(var i = 0;i<$arr.length;i++) {
      $input = $($arr[i]).prop('checked',checked);                
    }
}

$(document).ready(function(){
   $('.uppercase').keyup(function(e) {       
        if(e.keyCode>=65 && e.keyCode <= 90) 
            this.value = this.value.toUpperCase();
        return true;
    });
    
    if($().mask) {
        $('.date-picker').mask('99/99/9999');
        $('.hora').mask('99:99:99');
    }
    if($().datepicker) {
        if($.fn.datepicker.defaults)
            $.fn.datepicker.defaults.format = "dd/mm/yyyy";
        $('.date-picker').datepicker({
            language: 'es',
            locale:'es-AR',
            format:'dd/mm/yyyy',
            dateFormat:'dd/mm/yyyy',
            autoclose: true
        });
    }
//    if($().datepicker) {
//        $('.date-picker').datepicker({
//            language: 'es'
//        });
//        $('.date-picker').on('changeDate', function(ev){
//            $(this).datepicker('hide');
//        });
//    }
    
     $('#btnHideData').click(function(){
        $('#btnHideData').toggleClass("fa-chevron-down");
        $('#btnHideData').toggleClass("fa-chevron-up");
       $('#data').slideToggle();
    });
    
    if(window.Handlebars) {
        window.Handlebars.registerHelper('select', function( value, options ){
            var $el = $('<select />').html( options.fn(this) );
            $el.find('[value="' + value + '"]').attr({'selected':'selected'});
            return $el.html();
        });
        window.Handlebars.registerHelper('convertirFecha', convertirFecha);
    }
});

 function validarCampo (campo,mensajeError,check){     
    if(check ===undefined) { check = false;}          
    if(campo ===undefined || campo.val()=== "" || check(campo)){
      bootbox.alert(mensajeError);
      campo.parent().addClass("has-error");
      return false;
  } else campo.parent().removeClass("has-error");
  return true;
}
//function validarCampoFecha(campo,mensajeError){
//     if(campo===undefined || campo.val()==="" || !validarFecha(campo.val())){            
//      bootbox.alert(mensajeError);
//      campo.parent().addClass("has-error");
//      return false;
//  } else campo.parent().removeClass("has-error");
//  return true;
//}


function validarCampoFecha(e){
    return !validarFecha(e.val());
}
function validarNoCero(e){return parseInt(e.val())===0}    

function escapeHTML(mystring){
    return mystring.replace(/&/g, "&amp;").replace(/>/g, "&gt;").replace(/</g, "&lt;").replace(/"/g, "&quot;");
}

function setLoader($tabla){
    var cant_cols = $tabla.find('thead th').length;
    $tabla.find('tbody').html("<tr><td colspan='" + cant_cols + "'><center><img src='images/ajax-loader.gif'/></center></td></tr>");
}

function initDialog(){
//    if($().mask) {       
//        $('.date-picker').mask('99/99/9999');
//        $('.hora').mask('99:99:99');
//    }
    if($().datepicker) {
        $('.date-picker').datepicker({
             language: 'es',
             locale:'es-AR',
             format:'dd/mm/yyyy',
             dateFormat:'dd/mm/yyyy',
             autoclose: true
         });
     }
    
}