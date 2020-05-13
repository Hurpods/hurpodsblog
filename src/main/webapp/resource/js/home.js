function getDaily() {
    $.ajax({
        url:"https://v1.hitokoto.cn/?c=d",
        dataType:"json",
        async:false,
        success:function(data){
            let str;
            if(data.from_who!==null){
                str="——《"+data.from+"》"+data.from_who;
            }else{
                str="——《"+data.from+"》";
            }
            $("#daily-words").html(data.hitokoto);
            $("#daily-sign").html(str);
        }
    });
}

$(document).ready(getDaily());

$(document).ready(function(){
    $(".article-style2 .tags .tagList li a").each(function(){
        if($(this).attr("href").match(/\d+/g)==4){
            $(this).parents(".article-style2").remove();
        }
    });
});