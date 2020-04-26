function getTalks() {
    $.ajax({
        url:"https://v1.hitokoto.cn/?c=d",
        dataType:"json",
        async:true,
        success:function(data){
            var str;
            if(data.from_who!==null){
                str="——《"+data.from+"》"+data.from_who;
            }else{
                str="——《"+data.from+"》";
            }
            $("#content-words").html(data.hitokoto);
            $("#content-sign").html(str);
        }
    })
}

$(document).ready(getTalks());
