/**
 * ȫѡ by hongten(hongtenzone@foxmail.com)
 */
function selectAll() {
    $("input:checkbox").attr("checked", "checked");
};

/**
 * ȫ��ѡ by hongten(hongtenzone@foxmail.com)
 */
function selectNone() {
    $("input:checkbox").removeAttr("checked");
};

/**
 * ȫѡ��ѡ���¼�
 */
$("#check_all_top").change(function() {
    if ($("#check_all_top").attr("checked") == "checked") {
        selectAll();
    } else {
        selectNone();
    }
});

/**
 * ȫѡ��ѡ���¼�
 */
$("#check_all_foot").change(function() {
    if ($("#check_all_foot").attr("checked") == "checked") {
        selectAll();
    } else {
        selectNone();
    }
});

/**
 * �̼�ȫѡ��
 * 
 * @date 2012-12-29
 * @author hongten(hongtenzone@foxmail.com)
 * 
 * @param id
 */
function check_business_all(id) {
    if (id == null || id == "") {
        alert("id����Ϊ��");
    } else {
        if($("#check_all_business_"+id).attr("checked") == "checked"){
            $("#business_bar_"+id+" :checkbox").each(function(i) {
                $(this).attr("checked","checked");
            });
        }else{
            $("#business_bar_"+id+" :checkbox").each(function(i) {
                $(this).attr("checked",false);
            });
        }
        var flag = false;
        
        $("input[name='myBusiness']").each(function(i) {
            if($(this).attr("checked") == "checked"){
                flag = true;
            }else{
                flag = false;
                return false; 
            }
        });
        
        if(flag){
            $("#check_all_top").attr("checked","checked");
            $("#check_all_foot").attr("checked","checked");
        }else{
            $("#check_all_top").attr("checked",false);
            $("#check_all_foot").attr("checked",false);
        }
    }
};

/**
 * �ֽ�ȯȫѡ��
 * 
 * @date 2012-12-29
 * @author hongten(hongtenzone@foxmail.com)
 * 
 * @param id
 */
function check_cashticket(id, b_id) {
    if (id == null || id == "" || b_id == null || b_id == "") {
        alert("id����Ϊ��");
    } else {
        var flag = false;
        $("#business_bar_"+b_id+" tr[id] :checkbox").each(function(i) {
            if($(this).attr("checked") == "checked"){
                flag = true;
            }else{
                flag = false;
                return false; 
            }
        });
        if(flag){
            $("#check_all_business_"+b_id).attr("checked","checked");
        }else{
            $("#check_all_business_"+b_id).attr("checked",false);
        }
        
        //���³�ʼ��flag
        flag = false;
        
        $("input[name='myBusiness']").each(function(i) {
            if($(this).attr("checked") == "checked"){
                flag = true;
            }else{
                flag = false;
                return false; 
            }
        });
        
        if(flag){
            $("#check_all_top").attr("checked","checked");
            $("#check_all_foot").attr("checked","checked");
        }else{
            $("#check_all_top").attr("checked",false);
            $("#check_all_foot").attr("checked",false);
        }
    }
};