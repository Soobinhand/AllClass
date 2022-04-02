var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            var email = $('#email').val();
            var pemail = $('#pemail').val();
            if (email === pemail){
                _this.update();
            }else{
                alert("권한이 없습니다.");
            }
        });

        $('#btn-delete').on('click', function () {
            var email = $('#email').val();
            var pemail = $('#pemail').val();
            if (email === pemail){
                _this.delete();
            }else{
                alert("권한이 없습니다.");
            }
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val(),
            categoryid: $('#categoryid').val(),
            email: $('#email').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/post/',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/' + data.categoryid;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();
        var categoryid = $('#categoryid').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/post/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/' + categoryid;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();
        var data = {
            categoryid: $('#categoryid').val()
        };
        $.ajax({
            type: 'DELETE',
            url: '/api/v1/post/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다..');
            window.location.href = '/' + data.categoryid;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

main.init();