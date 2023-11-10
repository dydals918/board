var title = document.getElementsByClassName('.frm_input');
var context = document.getElementsByClassName('.frm_txtarea');



function submit_click(){
    if(title.value == null && context.value == null){
         alert("제목과 내용을 입력하세요.");
    }else if(title.value == null){
         alert("제목을 입력하세요.");
    }else if(context.value == null){
        alert("내용을 입력하세요.");
    }
}
