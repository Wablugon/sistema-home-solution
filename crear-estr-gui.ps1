$archivos = @(
"src/gui/FormularioTarea.java",
"src/gui/InformacionProyecto.java",
"src/gui/GestionProyectos.java",
"src/gui/FormularioProyectoFinalizado.java",
"src/gui/FormularioProyecto.java",
"src/gui/ListaProyectos.java",
"src/gui/GestionEmpleados.java",
"src/gui/FormularioEmpleado.java",
"src/gui/PanelPrincipal.java",
"src/gui/PanelManager.java" 

)

foreach($archivo in $archivos){

    if(Test-Path $archivo){
        continue
    }

    $carpeta = Split-Path $archivo

    $nombreClase = [System.IO.Path]::GetFileNameWithoutExtension($archivo)

    $paquete = $carpeta.Replace("\","/").Replace("src/","").Replace("/", ".")

    if($nombreClase.StartsWith("I")){

@"
package $paquete;

public interface $nombreClase {

}
"@ | Set-Content $archivo

    }
    else{

@"
package $paquete;

public class $nombreClase {

}
"@ | Set-Content $archivo

    }

}