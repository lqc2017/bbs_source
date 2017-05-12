/**
 * @license Copyright (c) 2003-2017, CKSource - Frederico Knabben. All rights
 *          reserved. For licensing, see LICENSE.md or
 *          http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function(config) {
	config.language = 'zh-cn';

	config.toolbarGroups = [ {
		name : 'basicstyles',
		groups : [ 'basicstyles', 'cleanup' ]
	}, {
		name : 'clipboard',
		groups : [ 'clipboard', 'undo' ]
	}, {
		name : 'links',
		groups : [ 'links' ]
	}, {
		name : 'insert',
		groups : [ 'insert' ]
	}, {
		name : 'document',
		groups : [ 'mode', 'document', 'doctools' ]
	}, {
		name : 'editing',
		groups : [ 'find', 'selection', 'spellchecker', 'editing' ]
	}, {
		name : 'forms',
		groups : [ 'forms' ]
	}, '/', {
		name : 'paragraph',
		groups : [ 'list', 'indent', 'blocks', 'align', 'bidi', 'paragraph' ]
	}, '/', {
		name : 'styles',
		groups : [ 'styles' ]
	}, {
		name : 'colors',
		groups : [ 'colors' ]
	}, {
		name : 'tools',
		groups : [ 'tools' ]
	}, {
		name : 'others',
		groups : [ 'others' ]
	}, {
		name : 'about',
		groups : [ 'about' ]
	} ];

	config.removeButtons = 'NewPage,Save,Source,Templates,Preview,Print,Paste,PasteText,PasteFromWord,Subscript,Superscript,RemoveFormat,Form,Checkbox,Radio,TextField,Textarea,Select,Button,ImageButton,HiddenField,CopyFormatting,Find,Replace,SelectAll,Scayt,NumberedList,BulletedList,Indent,Outdent,Blockquote,CreateDiv,JustifyCenter,JustifyLeft,JustifyRight,JustifyBlock,Language,BidiRtl,BidiLtr,Flash,Table,HorizontalRule,Smiley,SpecialChar,PageBreak,Iframe,Styles,TextColor,Maximize,ShowBlocks,BGColor,Format,Font,FontSize,About';
	
	config.filebrowserUploadUrl="/question/ckeditorUpload";
};
