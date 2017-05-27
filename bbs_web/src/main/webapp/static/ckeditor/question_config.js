/**
 * @license Copyright (c) 2003-2017, CKSource - Frederico Knabben. All rights
 *          reserved. For licensing, see LICENSE.md or
 *          http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function(config) {
	config.extraPlugins = 'codesnippet';
	config.language = 'zh-cn';
    config.height = 100;

	config.toolbarGroups = [
		{ name: 'document', groups: [ 'mode', 'document', 'doctools' ] },
		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
		{ name: 'clipboard', groups: [ 'clipboard', 'undo' ] },
		{ name: 'editing', groups: [ 'find', 'selection', 'spellchecker', 'editing' ] },
		{ name: 'forms', groups: [ 'forms' ] },
		{ name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi', 'paragraph' ] },
		{ name: 'links', groups: [ 'links' ] },
		{ name: 'insert', groups: [ 'insert' ] },
		'/',
		{ name: 'styles', groups: [ 'styles' ] },
		{ name: 'colors', groups: [ 'colors' ] },
		{ name: 'tools', groups: [ 'tools' ] },
		{ name: 'others', groups: [ 'others' ] },
		{ name: 'about', groups: [ 'about' ] }
	];

	config.removeButtons = 'Source,Save,NewPage,Preview,Print,PasteText,PasteFromWord,Find,Replace,SelectAll,Scayt,Form,Checkbox,Radio,TextField,Textarea,Select,Button,ImageButton,HiddenField,Templates,Subscript,Superscript,RemoveFormat,CopyFormatting,NumberedList,BulletedList,Indent,Outdent,Blockquote,CreateDiv,JustifyCenter,JustifyLeft,JustifyRight,JustifyBlock,Language,BidiRtl,BidiLtr,Anchor,Cut,Flash,Table,Smiley,SpecialChar,PageBreak,Iframe,Styles,TextColor,BGColor,ShowBlocks,Maximize,About,Format,Font,FontSize';

	config.filebrowserUploadUrl="/ckeditorUpload";
};
