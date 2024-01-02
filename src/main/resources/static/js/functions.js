

function totalPaginas()
{
    //tomamos valores de contadores del html, el actual por etiqueta y el inicial por tagname
    var contFinal=parseInt(document.getElementById('contadorTotal').value);
    let obtenerDato = document.getElementsByTagName("td");
    var contInicio=parseInt(obtenerDato[14].innerHTML );
    var paginas=contFinal-contInicio;
    console.log(contInicio);
    //calculamos valores de scanner
    var contFinalScan=parseInt(document.getElementById('contadorScan').value);
    let obtenerDatoScan = document.getElementsByTagName("td");
    var contInicioScan=parseInt(obtenerDato[16].innerHTML );
    console.log(contInicioScan);
    var scan =  contFinalScan-contInicioScan;

    //tomamos las fechas anterior(por etiqueta) y el actual por tagname
    var date =(obtenerDato[12].innerHTML);
    console.log(date);
    var date_2 = new Date(document.getElementById('fechaEvento').value);
    console.log(date_2);
    //Convertimos dd/mm/yyyy a formato string largo
    var dateString = date;
    var dateParts = dateString.split("/");
    // month is 0-based, that's why we need dataParts[1] - 1
    var date_1 = new Date(+dateParts[2], dateParts[1] - 1, +dateParts[0]);
    console.log(date_1);
    //conveetimos a milisegundos para poder restar las fechas
    var day_as_milliseconds = 86400000;
    var diff_in_millisenconds = date_2 - date_1;
    var diff_in_days = Math.ceil(diff_in_millisenconds / day_as_milliseconds);

    console.log( diff_in_days );
    if ((scan < 0) || (diff_in_days <0) || (paginas<0)) {
         alert ("Hay un resultado en valor Negativo, por favor revise los datos introducidos");
    }
    alert ("El numero en JS de Páginas es: "+paginas+"\n"+ "Scan : "+scan+ "\n"+ "Dias : "+diff_in_days );

}
