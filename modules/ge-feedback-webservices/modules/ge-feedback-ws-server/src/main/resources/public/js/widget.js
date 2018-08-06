import $ from 'jquery';
import 'jquery-bar-rating';
import '../css/widget.less';

import tpl from '../widget.html';



$(function() {
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
