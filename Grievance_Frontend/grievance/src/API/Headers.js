export const headers = () =>{
    console.log(localStorage.getItem('userDetails'))
    return JSON.parse(localStorage.getItem('userDetails'))
}