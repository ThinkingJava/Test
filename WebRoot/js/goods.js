/**
 * 全选 by hongten(hongtenzone@foxmail.com)
 */
function selectAll() {
    $("input:checkbox").attr("checked", "checked");
};

/**
 * 全不选 by hongten(hongtenzone@foxmail.com)
 */
function selectNone() {
    $("input:checkbox").removeAttr("checked");
};

/**
 * 全选框选择事件
 */
$("#check_all_top").change(function() {
    if ($("#check_all_top").attr("checked") == "checked") {
        selectAll();
    } else {
        selectNone();
    }
});

/**
 * 全选框选择事件
 */
$("#check_all_foot").change(function() {
    if ($("#check_all_foot").attr("checked") == "checked") {
        selectAll();
    } else {
        selectNone();
    }
});

/**
 * 商家全选框
 * 
 * @date 2012-12-29
 * @author hongten(hongtenzone@foxmail.com)
 * 
 * @param id
 */
function check_business_all(id) {
    if (id == null || id == "") {
        alert("id不能为空");
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
 * 现金券全选框
 * 
 * @date 2012-12-29
 * @author hongten(hongtenzone@foxmail.com)
 * 
 * @param id
 */
function check_cashticket(id, b_id) {
    if (id == null || id == "" || b_id == null || b_id == "") {
        alert("id不能为空");
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
        
        //重新初始化flag
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