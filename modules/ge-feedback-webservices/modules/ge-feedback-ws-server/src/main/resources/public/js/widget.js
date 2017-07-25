(function() {

    var $ = require('jquery');
    require('jquery-bar-rating');

    var tpl = '<div id="feedback-widget">' //
            + '     <button id="fbw-main-btn" type="button"><img src="%%INC:images/feedback.png%%" /></button>' //
            + '     <div id="fbw-wrapper">' //
            + '         <form>' //
            + '             <div id="fbw-rating">' //
            + '                 <span id="fbw-rating-label"></span>' //
            + '                 <div id="fbw-rating-input">' //
            + '                     <select name="rating">' //
            + '                         <option value="1">A revoir</option>' //
            + '                         <option value="2">Tr&egrave;s insatisfait</option>' //
            + '                         <option value="3">Plut&ocirc;t satisfait</option>' //
            + '                         <option value="4">Satisfait</option>' //
            + '                         <option value="5">Tr&egrave;s satisfait</option>' //
            + '                     </select>' //
            + '                 </div>' //
            + '             </div>' //
            + '             <div id="fbw-comment">' //
            + '                 <label for="">Des suggestions ?</label>' //
            + '                 <textarea name="comment" id ="user-feedback-text"></textarea>' //
            + '                 <button type="submit" id="user-feedback-submit">Valider</button>' //
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
            modal : null,
            rating : $('> form > #fbw-rating', wrapper),
            comment : $('> form > #fbw-comment', wrapper),
            thanks : $('> form > #fbw-thanks', wrapper),
            label : null,
            defaultLabel : 'Notez cette page',
            timeout : -1,
            show : function() {
                uid = null;
                this.modal = $('<div></div>').insertBefore(this.root).css({
                    'background-color' : 'transparent',
                    'bottom' : '0px',
                    'left' : '0px',
                    'position' : 'fixed',
                    'right' : '0px',
                    'top' : '0px',
                }).on('click', function (evt) {
                    widget.hide();
                });
                $('> form', wrapper).get(0).reset();
                $('> #fbw-rating-input select[name="rating"]', widget.rating).barrating('clear');
                this.label = this.defaultLabel;
                this.rating.show();
                this.comment.hide();
                this.thanks.hide();
                this.root.addClass('expanded').width(this.root.width());
            },
            hide : function() {
                this.root.off('mouseout').off('mouseover');
                this.root.removeClass('expanded').css('width', '');
                this.modal.remove();
                this.modal = null;
            }
        };

        function buildServiceUri(data) {
            var serviceBaseUri;
            if (uid) {
                serviceBaseUri = '${ws.feedback.public.url}/public/v1/feedback/update?id=' + uid + '&';
            } else {
                serviceBaseUri = '${ws.feedback.public.url}/public/v1/feedback/create?';
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
            return false;
        });

        $(document).on('click', function(evt) {
        });

        $('> #fbw-rating-input select[name="rating"]', widget.rating).barrating({
            theme : 'css-stars',
            onSelect : function(value, text, evt) {
                $('> #fbw-rating-label', widget.rating).text(widget.label = text);

                $.ajax(buildServiceUri({
                    rate : value,
                    comment : '',
                    page : window.location.href
                })).done(function (data) {
                    uid = data;
                });
                $(widget.comment).show();
            },
            onClear : function (value, text) {
                $('#fbw-rating-label', widget.rating).text(widget.defaultLabel);
            }
        });

        $('> #fbw-rating-input .br-widget > a').each(function() {
            var currRating = $(this);
            currRating.attr('id', 'user-feedback-rating-' + currRating.data('rating-value'));
        });

        $('> #fbw-rating-input .br-widget > a', widget.rating).on('mouseenter.feedback', function () {
            $('#fbw-rating-label', widget.rating).text($(this).data('rating-text'));
        }).on('mouseleave.feedback', function () {
            $('#fbw-rating-label', widget.rating).text(widget.label);
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

            widget.root.on('mouseleave.feedback', function (evt) {
                widget.timeout = setTimeout(function () {
                    widget.hide();
                }, 2000);
            }).on('mouseenter.feedback', function (evt) {
                clearTimeout(widget.timeout);
            });
        });
    });

})();
