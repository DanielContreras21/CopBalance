export function Hola({isExist}){
    
    let abc
    isExist ? abc = "Si Existe" : abc = "No Existe"
    

    return(
        <h2>{abc}</h2>
    )
}