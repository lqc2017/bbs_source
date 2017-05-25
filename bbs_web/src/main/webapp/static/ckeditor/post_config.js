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
		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
		{ name: 'colors', groups: [ 'colors' ] },
		{ name: 'links', groups: [ 'links' ] },
		{ name: 'insert', groups: [ 'insert' ] },
		{ name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi', 'paragraph' ] },
		{ name: 'clipboard', groups: [ 'clipboard', 'undo' ] },
		{ name: 'document', groups: [ 'mode', 'document', 'doctools' ] },
		{ name: 'editing', groups: [ 'find', 'selection', 'spellchecker', 'editing' ] },
		{ name: 'forms', groups: [ 'forms' ] },
		{ name: 'styles', groups: [ 'styles' ] },
		{ name: 'tools', groups: [ 'tools' ] },
		{ name: 'others', groups: [ 'others' ] },
		{ name: 'about', groups: [ 'about' ] }
	];

    config.removeButtons = 'Source,Save,Find,SelectAll,Scayt,Form,Superscript,Subscript,PasteFromWord,PasteText,Templates,Cut,Replace,Radio,TextField,Textarea,Button,Select,ImageButton,HiddenField,Checkbox,RemoveFormat,CopyFormatting,NumberedList,BulletedList,Indent,Outdent,Blockquote,CreateDiv,BidiLtr,Anchor,Language,BidiRtl,Flash,Table,SpecialChar,PageBreak,Iframe,BGColor,ShowBlocks,Maximize,About,NewPage,Print,Styles,Format,Font,FontSize';


	config.filebrowserUploadUrl="/ckeditorUpload";
};
