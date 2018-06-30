<script type="text/javascript">
    $.ajax({
                type: 'GET',
                url: __ctx + '/hr/recruitmentNeeds/recruitmentNeeds/queryOrgExisNum',
                dataType: 'json',
                async: true, // 默认为true，默认为true时，所有请求均为异步请求，如果需要发送同步请求，需设置为false,
                timeout: 5000,
                data: {orgId:'10000001112349'},
                beforeSend: function () {
                    // 在发送请求前就开始执行 （一般用来显示loading图）
                    //$("#newsBody_can>li").eq(indexNum).children(".table").children("tbody").remove();
                },
                success: function (data) {
                    alert(data);
                    // 发送请求成功后开始执行，data是请求成功后，返回的数据
                    $.each(eval(data).rows, function(index,item){

                        var createTime = getDate(item.createTime);
                        var address = "/x5/flow/instance/instanceGet?id=" + item.id;
                        var block = '<tr openFull="'+ address +'"><td>'+ (index+2)+'</td><td><ul class="dbTableContent newsLink"><li class="dbTableContent">'+ item.subject +'</li></ul></td><td class="dbTableContent news_can_num">'+ createTime +'</td></tr>';
                        $("#newsBody_can>li").eq(indexNum).children(".table").append(block);
                        
                    }); 
                },
                complete: function () {
                        //当请求完成后开始执行，无论成功或是失败都会执行 （一般用来隐藏loading图）
                },
                error: function (xhr, textStatus, errorThrown) {
                        //  请求失败后就开始执行，请求超时后，在这里执行请求超时后要执行的函数
                }
            }).done(function () {
                    // 这个函数是在ajax数据加载完之后，对数据进行的判断，在涉及到对ajax数据进行操作无效时，在这个函数里面写是可以起到效果的
            })


</script>	