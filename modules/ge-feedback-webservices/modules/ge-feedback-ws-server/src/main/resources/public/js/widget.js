(function() {

    var $ = require('jquery');
    require('jquery-bar-rating');

    var tpl = '<div id="feedback-widget">' //
            + '     <button id="fbw-main-btn" type="button"><img src="%%INC:images/feedback.png%%" /></button>' //
            + '     <div id="fbw-wrapper">' //
            + '         <form>' //
            + '             <div id="fbw-rating">' //
            + '                 <span id="fbw-rating-label">Notez cette page</span>' //
            + '                 <div id="fbx-rating-input">' //
            + '                     <select name="rating">' //
            + '                         <option value="1">A revoir</option>' //
            + '                         <option value="2">Tr&egrave;s insatisfait</option>' //
            + '                         <option value="3">Plut&ocirc;t insatisfait</option>' //
            + '                         <option value="4">Plut&ocirc;t satisfait</option>' //
            + '                         <option value="5">Tr&egrave;s satisfait</option>' //
            + '                     </select>' //
            + '                 </div>' //
            + '             </div>' //
            + '             <div id="fbw-comment">' //
            + '                 <label for="">Des suggestions ?</label>' //
            + '                 <textarea name="comment"></textarea>' //
            + '                 <button type="submit">Valider</button>' //
            + '             </div>' //
            + '             <div id="fbw-thanks">' //
            + '                 Merci !' //
            + '             </div>' //
            + '         </form>' //
            + '     </div>' //
            + '</div>';

    $(function() {
        $('<link rel="stylesheet" href="%%INC:css/widget.css%%" />').appendTo('head');
        var root = $(tpl).appendTo('body'), wrapper = $('> #fbw-wrapper', root);
        var uid;

        var widget = {
            root : root,
            wrapper : wrapper,
            rating : $('> form > #fbw-rating', wrapper),
            comment : $('> form > #fbw-comment', wrapper),
            thanks : $('> form > #fbw-thanks', wrapper),
            show : function() {
                uid = null;
                $('> form', wrapper).get(0).reset();
                $('> #fbx-rating-input select[name="rating"]', widget.rating).barrating('set', 3);
                this.rating.show();
                this.comment.hide();
                this.thanks.hide();
                this.root.addClass('expanded').css('width', root.width());
            },
            hide : function() {
                this.root.removeClass('expanded').css('width', '');
            }
        };

        function buildServiceUri(data) {
            var serviceBaseUri;
            if (uid) {
                serviceBaseUri = '${ws.feedback.public.url}/v1/feedback/update?id=' + uid + '&';
            } else {
                serviceBaseUri = '${ws.feedback.public.url}/v1/feedback/create?';
            }

            return {
                url: serviceBaseUri + $.param(data),
                dataType: 'text',
                method: uid ? 'PUT' : 'POST'
            };
        }

        $('> #fbw-main-btn', widget.root).on('click', function(evt) {
            evt.preventDefault();
            if (widget.root.hasClass('expanded')) {
                widget.hide();
            } else {
                widget.show();
            }
        });

        $(document).on('click', function(evt) {
        });

        $('> #fbx-rating-input select[name="rating"]', widget.rating).barrating({
            theme : 'css-stars',
            onSelect : function(value, text, evt) {
                $('> #fbw-rating-label', widget.rating).text(text);

                
                $.ajax(buildServiceUri({
                    rate : value,
                    comment : '',
                    page : window.location.href
                })).done(function (data) {
                    uid = data;
                });
                $(widget.comment).show();
            }
        });

        $('> form', widget.wrapper).on('submit', function(evt) {
            var form = $(this);
            evt.preventDefault();

            $.ajax(buildServiceUri({
                rate: $('select[name="rating"]', form).val(),
                comment: $('textarea[name="comment"]', form).val(),
                page: window.location.href
            }));

            widget.rating.hide();
            widget.comment.hide();
            widget.thanks.show();
        });
    });

})();
