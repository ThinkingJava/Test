window.onload = function(){
 //�Ͱ汾��IE�������֧��getElementByClassName���������Ǽ�����,��д������
 if (!document.getElementByClassName) {
  document.getElementByClassName =function(cls){
   var ret = [];
   var clsElments = document.getElementsByTagName('*');
   for (var i = 0, len = clsElments.length; i < len; i++) {
    //����һ����ǩ�ж��class�����,������������ʽ���һ��
    if (clsElments[i].className == cls 
     || (clsElments[i].className.indexOf(cls + " ") >= 0)
     || (clsElments[i].className.indexOf(" " + cls + " ") >= 0)
     || (clsElments[i].className.indexOf(" " + cls) >= 0)) 
    {
     ret.push(clsElments[i]);
    }
   }
   return ret;
  }
 
 }
 
 var cartTable = document.getElementById("cartTable");
 var tr = cartTable.children[1].rows; //table��ǩ���е����ԣ�rows���Ի�ñ��Ԫ�ص�����tr�С�
 var checkInput = document.getElementByClassName('check');//������еĵ�ѡ��
 var checkAllInput = document.getElementByClassName('check_all');//������еĵ�ѡ��
 var priceTotle = document.getElementById("priceTotle");//�ܼ�
 var selectTotle = document.getElementById("selectTotle");//��ѡ��Ʒ
 var selected = document.getElementById("selected");
 var footer = document.getElementById("footer");//�ײ���ǩ
 var selectedViewList = document.getElementById("selectedViewList");//�ײ���ǩ
 var deleteAll = document.getElementById("deleteAll");
 
 
 
 //�����ܼ۸������
 function getTotle(){
  var selectNum = 0;//����
  var priceNum = 0;//�۸�
  var HTMLstr = ""; //����ͼ���ַ���ƴ��
  for (var i = 0,len = tr.length; i < len; i++) {
   if (tr[i].getElementsByTagName("input")[0].checked) {
    tr[i].className ="on";
    selectNum += parseInt(tr[i].getElementsByTagName("input")[1].value);
    priceNum += parseFloat(tr[i].cells[4].innerHTML);
 
 
    //ƴ���ַ�����ʾ�Ѿ�ѡ�����Ʒ 
  //  HTMLstr += '<div><img src="'+ tr[i].getElementsByTagName('img')[0].src +'" height="10" width="10"/><span class ="del" index ="'+ i +'">ȡ��ѡ��</span></div>';
    HTMLstr +='<s:param name="'+tr[i].getElementsByTagName('input')[1].name+'" value="'+tr[i].getElementsByTagName('input')[1].value+'"></s:param>';
   }
   else{
    tr[i].className = "";
   }
  }
  selectTotle.innerHTML = selectNum;
  priceTotle.innerHTML = priceNum.toFixed(2);//������λС��
  selectedViewList.innerHTML = HTMLstr;
 }
 
 //����С�Ƽ۸�
 function getSubTotle(tr){
  var tds = tr.cells;
  var price = parseFloat(tds[2].innerHTML);
  var num = parseInt(tr.getElementsByTagName("input")[1].value);
  var subTotle = parseFloat(price * num).toFixed(2);
  tds[4].innerHTML = subTotle;
 }
 
 //��ѡ��󶨵����¼�
 for (var i = 0, len = checkInput.length; i < len; i++){
  checkInput[i].onclick =function (){
   //�ж�ȫѡ��ť�����
   if (this.className == "check_all check") {
    for (var j = 0; j < len; j++){
     checkInput[j].checked = this.checked;
    }
   }
   if (this.checked == false) {
    for (var k = 0,len2 = checkAllInput.length; k < len2; k++){
     checkAllInput[k].checked = false;
    }
   }
   getTotle();
  }
 }
 
 
 
 //���Ƶײ���ǩ����ʾ
 selected.onclick = function(){
  if (footer.className == "footer") {
   footer.className == "footer show";
  } else {
   footer.className == "footer"; 
  }
 }
 
 
 //ͼƬ����ͼ��ȡ��ѡ��ť����,eΪ�¼�����
 selectedViewList.onclick = function(e){
  //���ݵͰ汾��IE
/*  if (e){
   e = e;
  }else{
   e = window.event;
  } */
  var e = e || window.event;
  var el = e.srcElement;
  if (el.className == "del") {
   var index = el.getAttribute("index");
   var input = tr[index].getElementsByTagName("input")[0];
   input.checked = false;
   input.onclick();
  }
 }
 
 //ʵ�ּӼ���ɾ��������ͬ�����¼�����ķ���ʵ��
 for (var i = 0, len3 = tr.length; i < len3; i++){
  tr[i].onclick = function(e){
   var e = e || window.event;
   var el = e.srcElement;
   var clsName = el.className;
   var input = this.getElementsByTagName("input")[1];
   var inputValue = parseInt(input.value);
   var reduce = this.getElementsByTagName("span")[1];
   switch (clsName){
    case "add":
     /*parseInt(inputValue) ++;*/
     input.value = inputValue + 1;
     reduce.innerHTML ="-";
     getSubTotle(this);
     break;
    case "reduce":
     if(inputValue >= 1){
      input.value = inputValue - 1;
     }else{
      reduce.innerHTML ="";
     }
     getSubTotle(this);     
     break;
    case "delete":
     var conf = confirm("ȷ��ɾ�������Ʒ��");
     if (conf) {
      this.parentNode.removeChild(this);
     }
     break;
    default:
     break;
   }
   getTotle();
  }
  //������ֶ�������Ʒ����
  tr[i].getElementsByTagName("input")[1].onkeyup = function(){
   var val = this.value;
   var tr = this.parentNode.parentNode;
   if (isNaN(val) || val < 0 ) {
    val = 1;
   }
   this.value = val;
   getSubTotle(tr);
  }
 }
 
 //ȫѡɾ��
 deleteAll.onclick = function(){
  if (selectTotle.innerHTML !="0") {
   var conf = confirm("ȷ��ɾ����Щ��Ʒ��");
   if (conf) {
    for (var i = 0, len = tr.length; i < len; i++) {
     var input = tr[i].getElementsByTagName("input")[0];
     if(input.checked){
      tr[i].parentNode.removeChild(tr[i]);
     }
    }
   }
  }  
 }
}
 
//ȡ��ѡ��--�����¼�����---�ŵ���Ԫ���ϡ�
