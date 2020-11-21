window.onload=function(){
    lay2=document.getElementById("lay2");
    // show();//0直接调用方法
    A=setInterval("show()",1000);
    i=0;
//手动控制lay2的滚动条交给一个方法自动执行
//定义方法控制滚动条
mc=document.getElementById("mc");
for(var j=0;j<mc.children.length;j++) {
    mc.children[j].style.top = 180 - j * 20 + "px";
    mc.children[j].style.left = 500 + j * 25 + "px";
}
    menu=document.getElementById("menu");
    menu_item=document.getElementById("menu_item");
    r=menu.getBoundingClientRect();
    menu_item.style.left=r.right+"px";
    menu_item.style.top=r.top+"px";
};
function loadItems(x,id){
    if(x==1){
    menu_item.style.display = "block";
    var xhr = new XMLHttpRequest();
    xhr.open("get","CatalogServlet?action=load&id="+id);
    xhr.send();
    xhr.onreadystatechange=function (){
        if(xhr.readyState==4 && xhr.status==200){
            menu_item.innerHTML="";
            var res = xhr.responseText;
            res = JSON.parse(res);
            for(var xb in res){
                //menu_item.innerHTML+=res[xb].cname+"==";
                var div = document.createElement("div");
                div.style.width="220px";
                div.style.height="60px";
                div.style.float="left";
                var div_a = document.createElement("div");
                div_a.style.width = "90px";
                div_a.style.height="45px";
                div_a.style.float="left";
                var img = document.createElement("img");
                img.style.width="40px"
                img.style.height="40px";
                img.src = res[xb].logo;
                div_a.appendChild(img);
                var div_b = document.createElement("div");
                div_b.style.width="130px";
                div_b.style.height="60px";
                div_b.style.float="left";
                div_b.style.fontSize="12px";
                div_b.style.marginTop="12px"
                // div_b.innerHTML=res[xb].cname;//createTextNode*/
                div.appendChild(div_a);
                div.appendChild(div_b);
                menu_item.appendChild(div);
                var a = document.createElement("a");
                a.setAttribute("href","GoodsServlet?action=select&cid="+res[xb].cparent+"&cid1="+res[xb].cid);
                a.style.color="black";
                a.innerText=res[xb].cname;
                div_b.appendChild(a);
                div.appendChild(div_a);
                div.appendChild(div_b);
                menu_item.appendChild(div);
            }
        }
        
    }
    }else{
        menu_item.style.display = "none";
    }
}

function show(){
    lay2.scrollLeft=i;//设置滚动条属性值，并完成赋值，不用单位
    i+=1200;//0 1200 2400 3600
    //重新从第一张图开始
    if(i==4800)
        i=0;
}
function showImage(x){
    var index = x.innerHTML;
    i=(index-1)*1200;
    lay2.scrollLeft=i;
}