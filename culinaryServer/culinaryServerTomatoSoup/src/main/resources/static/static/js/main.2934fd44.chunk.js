(window.webpackJsonp=window.webpackJsonp||[]).push([[0],{22:function(e,t,n){e.exports=n.p+"static/media/logo.af49c77d.svg"},23:function(e,t,n){e.exports=n(55)},31:function(e,t,n){},32:function(e,t,n){},55:function(e,t,n){"use strict";n.r(t);var r=n(0),a=n.n(r),c=n(21),o=n.n(c),i=(n(31),n(3)),u=(n(32),n(22)),s=n.n(u);function l(e){var t=e.gameStateSetter;return React.createElement("div",{className:"App-main-container"},React.createElement("img",{src:s.a,className:"App-logo",alt:"logo"}),React.createElement("p",{className:"App-big-name font-link-bold"},"Culinary arts"),React.createElement("p",{className:"App-small-name font-link-base"},"by Kotlin Course"),React.createElement("div",{className:"App-buttons-container App-display-flex"},React.createElement("button",{className:"App-button-base App-button-start",onClick:function(){return t(_.GAME)}})))}var p=n(7),f=n(1),m=n(6),d=function(e){return e[e.CITRUS_BASKET=0]="CITRUS_BASKET",e[e.BERRY_BASKET=1]="BERRY_BASKET",e[e.ROT_TOMATO=2]="ROT_TOMATO",e[e.FRESH_TOMATO=3]="FRESH_TOMATO",e[e.CUT_TOMATO=4]="CUT_TOMATO",e[e.ROT_CUCUMBER=5]="ROT_CUCUMBER",e[e.FRESH_CUCUMBER=6]="FRESH_CUCUMBER",e[e.CUT_CUCUMBER=7]="CUT_CUCUMBER",e[e.ROT_CARROT=8]="ROT_CARROT",e[e.FRESH_CARROT=9]="FRESH_CARROT",e[e.CUT_CARROT=10]="CUT_CARROT",e[e.BERRY=11]="BERRY",e[e.CITRUS=12]="CITRUS",e[e.SALT=13]="SALT",e[e.PEPPER=14]="PEPPER",e[e.OREGANO=15]="OREGANO",e}({}),b=n(4),h=n.n(b);function v(e){var t=e.product,n=e.inFridge,r=void 0!==n&&n,a="App-product-image-"+d[t];return React.createElement("div",{className:"App-product-image "+a+" "+(r?"App-product-fridge":"App-product-counter")})}function A(e){var t=e.products;var n=Math.ceil(t.length/2),r=t.slice(0,n),a=t.slice(n);return React.createElement("div",{className:"App-counter-container"},React.createElement("div",{className:"App-products-row App-products-row-top"},r.map(function(e,t){return React.createElement(v,{product:e})})),React.createElement("div",{className:"App-products-row App-products-row-bottom"},a.map(function(e,t){return React.createElement(v,{product:e})})))}function g(e){var t=e.blenderOptions,n=t.visible,r=t.full,a=t.shake,c=t.citrus,o=t.berry,i="App-blender-jar"+(r?"-full":"");return React.createElement("div",{className:"App-blender"+(n?"":" App-invisible")+(a?" App-animation-shake":"")},React.createElement("div",{className:"App-blender "+i}),!r&&React.createElement(React.Fragment,null,Array.from({length:c}).map(function(e,t){return React.createElement("div",{key:t,className:"App-blender App-blender-citrus ",style:{top:"".concat(-1*(c-t-1),"vmin")}})}),Array.from({length:o}).map(function(e,t){return React.createElement("div",{key:t,className:"App-blender App-blender-berry ",style:{top:"".concat(-1*(o-t-1),"vmin")}})})))}function R(e){var t=e.potOptions,n=t.visible,r=t.simmer,a=t.soup,c=t.pepper,o=t.salt,i=t.oregano,u=t.cucumber,s=t.tomato,l=t.carrot,p=t.soupHue,f="App-pot"+(a?"-soup":"-water");return React.createElement("div",{className:"App-pot"+(n?"":" App-invisible")+(r?" App-animation-simmer":"")},React.createElement("div",{className:"App-pot App-pot-back"}),!a&&React.createElement(React.Fragment,null,Array.from({length:u}).map(function(e,t){return React.createElement("div",{key:t,className:"App-pot App-pot-cucumber ",style:{top:"".concat(-1*(u-t-1),"vmin")}})}),Array.from({length:s}).map(function(e,t){return React.createElement("div",{key:t,className:"App-pot App-pot-tomato ",style:{top:"".concat(-1*(s-t-1),"vmin")}})}),Array.from({length:l}).map(function(e,t){return React.createElement("div",{key:t,className:"App-pot App-pot-carrot ",style:{top:"".concat(-1*(l-t-1),"vmin")}})})),React.createElement("div",{className:"App-pot "+f,style:{filter:"hue-rotate(".concat(p,"deg)")}}),React.createElement("div",{className:"App-pot App-pot-pepper "+(c?"":"App-invisible")}),React.createElement("div",{className:"App-pot App-pot-salt "+(o?"":"App-invisible")}),React.createElement("div",{className:"App-pot App-pot-oregano "+(i?"":"App-invisible")}))}function E(e){var t=e.basketOptions,n=t.type,r=t.visible,a=t.count,c="App-basket-".concat(n,"-");return React.createElement("div",{className:"App-basket "+(r?"":" App-invisible")},React.createElement("div",{className:"App-basket App-basket-back"}),a>0&&React.createElement(React.Fragment,null,Array.from({length:a}).map(function(e,t){return React.createElement("div",{key:t,className:"App-basket App-basket-item "+c+(a-t)})})),React.createElement("div",{className:"App-basket App-basket-front"}))}function O(e){var t=e.visible;return React.createElement("div",{className:"App-spices-shelf"+(t?"":" App-invisible")})}function y(e){var t=e.saladBowlOptions,n=t.visible,r=t.mixing,a=t.mixed,c=t.tomato,o=t.cucumber,i=t.carrot;return React.createElement("div",{className:"App-salad"+(n?"":" App-invisible")+(r?" App-animation-mixing":"")},a?React.createElement(React.Fragment,null,React.createElement("div",{className:"App-salad App-salad-mixed-tomato "+(c>0?"":"App-invisible")}),React.createElement("div",{className:"App-salad App-salad-mixed-cucumber "+(o>0?"":"App-invisible")}),React.createElement("div",{className:"App-salad App-salad-mixed-carrot "+(i>0?"":"App-invisible")})):React.createElement(React.Fragment,null,Array.from({length:i}).map(function(e,t){return React.createElement("div",{key:t,className:"App-salad App-salad-carrot ",style:{top:"".concat(-1*(i-t-1),"vmin")}})}),Array.from({length:c}).map(function(e,t){return React.createElement("div",{key:t,className:"App-salad App-salad-tomato ",style:{top:"".concat(-1*(c-t-1),"vmin")}})}),Array.from({length:o}).map(function(e,t){return React.createElement("div",{key:t,className:"App-salad App-salad-cucumber ",style:{top:"".concat(-1*(o-t-1),"vmin")}})})),React.createElement("div",{className:"App-salad App-salad-bowl "}))}function T(e){var t=e.products,n=Math.ceil(t.length/3),c=t.slice(0,n),o=t.slice(n,2*n),i=t.slice(2*n),u=Object(r.useCallback)(function(){var e=document.querySelector(".App-fridge"),t=document.querySelectorAll(".App-fridge-first-shelf"),n=document.querySelectorAll(".App-fridge-second-shelf"),r=document.querySelectorAll(".App-fridge-third-shelf");if(e){var a=e.clientHeight,c=.15*a,o=.3*a,i=.425*a;t.forEach(function(e){e.style.top="".concat(c,"px")}),n.forEach(function(e){e.style.top="".concat(o,"px")}),r.forEach(function(e){e.style.top="".concat(i,"px")})}},[t]);return Object(r.useEffect)(function(){u();var e=function(){u()};return window.addEventListener("resize",e),function(){window.removeEventListener("resize",e)}},[u]),a.a.createElement("div",{className:"App-fridge"},a.a.createElement("div",{className:"App-fridge App-fridge-back"}),c.map(function(e,t){return a.a.createElement("div",{style:{left:"".concat(2+2*t,"vmin")},className:"App-fridge-first-shelf"},a.a.createElement(v,{product:e,inFridge:!0}))}),o.map(function(e,t){return a.a.createElement("div",{style:{left:"".concat(2+2*t,"vmin")},className:"App-fridge-second-shelf"},a.a.createElement(v,{product:e,inFridge:!0}))}),i.map(function(e,t){return a.a.createElement("div",{style:{left:"".concat(2+2*t,"vmin")},key:"third-shelf-".concat(t),className:"App-fridge-third-shelf"},a.a.createElement(v,{product:e,inFridge:!0}))}))}function S(e,t){var n="undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(!n){if(Array.isArray(e)||(n=function(e,t){if(e){if("string"==typeof e)return C(e,t);var n={}.toString.call(e).slice(8,-1);return"Object"===n&&e.constructor&&(n=e.constructor.name),"Map"===n||"Set"===n?Array.from(e):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?C(e,t):void 0}}(e))||t&&e&&"number"==typeof e.length){n&&(e=n);var r=0,a=function(){};return{s:a,n:function(){return r>=e.length?{done:!0}:{done:!1,value:e[r++]}},e:function(e){throw e},f:a}}throw new TypeError("Invalid attempt to iterate non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}var c,o=!0,i=!1;return{s:function(){n=n.call(e)},n:function(){var e=n.next();return o=e.done,e},e:function(e){i=!0,c=e},f:function(){try{o||null==n.return||n.return()}finally{if(i)throw c}}}}function C(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=Array(t);n<t;n++)r[n]=e[n];return r}function N(){N=function(){return t};var e,t={},n=Object.prototype,r=n.hasOwnProperty,a=Object.defineProperty||function(e,t,n){e[t]=n.value},c="function"==typeof Symbol?Symbol:{},o=c.iterator||"@@iterator",i=c.asyncIterator||"@@asyncIterator",u=c.toStringTag||"@@toStringTag";function s(e,t,n){return Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}),e[t]}try{s({},"")}catch(e){s=function(e,t,n){return e[t]=n}}function l(e,t,n,r){var c=t&&t.prototype instanceof v?t:v,o=Object.create(c.prototype),i=new j(r||[]);return a(o,"_invoke",{value:C(e,n,i)}),o}function p(e,t,n){try{return{type:"normal",arg:e.call(t,n)}}catch(e){return{type:"throw",arg:e}}}t.wrap=l;var f="suspendedStart",m="suspendedYield",d="executing",b="completed",h={};function v(){}function A(){}function g(){}var R={};s(R,o,function(){return this});var E=Object.getPrototypeOf,O=E&&E(E(U([])));O&&O!==n&&r.call(O,o)&&(R=O);var y=g.prototype=v.prototype=Object.create(R);function T(e){["next","throw","return"].forEach(function(t){s(e,t,function(e){return this._invoke(t,e)})})}function S(e,t){function n(a,c,o,i){var u=p(e[a],e,c);if("throw"!==u.type){var s=u.arg,l=s.value;return l&&"object"==typeof l&&r.call(l,"__await")?t.resolve(l.__await).then(function(e){n("next",e,o,i)},function(e){n("throw",e,o,i)}):t.resolve(l).then(function(e){s.value=e,o(s)},function(e){return n("throw",e,o,i)})}i(u.arg)}var c;a(this,"_invoke",{value:function(e,r){function a(){return new t(function(t,a){n(e,r,t,a)})}return c=c?c.then(a,a):a()}})}function C(t,n,r){var a=f;return function(c,o){if(a===d)throw Error("Generator is already running");if(a===b){if("throw"===c)throw o;return{value:e,done:!0}}for(r.method=c,r.arg=o;;){var i=r.delegate;if(i){var u=k(i,r);if(u){if(u===h)continue;return u}}if("next"===r.method)r.sent=r._sent=r.arg;else if("throw"===r.method){if(a===f)throw a=b,r.arg;r.dispatchException(r.arg)}else"return"===r.method&&r.abrupt("return",r.arg);a=d;var s=p(t,n,r);if("normal"===s.type){if(a=r.done?b:m,s.arg===h)continue;return{value:s.arg,done:r.done}}"throw"===s.type&&(a=b,r.method="throw",r.arg=s.arg)}}}function k(t,n){var r=n.method,a=t.iterator[r];if(a===e)return n.delegate=null,"throw"===r&&t.iterator.return&&(n.method="return",n.arg=e,k(t,n),"throw"===n.method)||"return"!==r&&(n.method="throw",n.arg=new TypeError("The iterator does not provide a '"+r+"' method")),h;var c=p(a,t.iterator,n.arg);if("throw"===c.type)return n.method="throw",n.arg=c.arg,n.delegate=null,h;var o=c.arg;return o?o.done?(n[t.resultName]=o.value,n.next=t.nextLoc,"return"!==n.method&&(n.method="next",n.arg=e),n.delegate=null,h):o:(n.method="throw",n.arg=new TypeError("iterator result is not an object"),n.delegate=null,h)}function _(e){var t={tryLoc:e[0]};1 in e&&(t.catchLoc=e[1]),2 in e&&(t.finallyLoc=e[2],t.afterLoc=e[3]),this.tryEntries.push(t)}function w(e){var t=e.completion||{};t.type="normal",delete t.arg,e.completion=t}function j(e){this.tryEntries=[{tryLoc:"root"}],e.forEach(_,this),this.reset(!0)}function U(t){if(t||""===t){var n=t[o];if(n)return n.call(t);if("function"==typeof t.next)return t;if(!isNaN(t.length)){var a=-1,c=function n(){for(;++a<t.length;)if(r.call(t,a))return n.value=t[a],n.done=!1,n;return n.value=e,n.done=!0,n};return c.next=c}}throw new TypeError(typeof t+" is not iterable")}return A.prototype=g,a(y,"constructor",{value:g,configurable:!0}),a(g,"constructor",{value:A,configurable:!0}),A.displayName=s(g,u,"GeneratorFunction"),t.isGeneratorFunction=function(e){var t="function"==typeof e&&e.constructor;return!!t&&(t===A||"GeneratorFunction"===(t.displayName||t.name))},t.mark=function(e){return Object.setPrototypeOf?Object.setPrototypeOf(e,g):(e.__proto__=g,s(e,u,"GeneratorFunction")),e.prototype=Object.create(y),e},t.awrap=function(e){return{__await:e}},T(S.prototype),s(S.prototype,i,function(){return this}),t.AsyncIterator=S,t.async=function(e,n,r,a,c){void 0===c&&(c=Promise);var o=new S(l(e,n,r,a),c);return t.isGeneratorFunction(n)?o:o.next().then(function(e){return e.done?e.value:o.next()})},T(y),s(y,u,"Generator"),s(y,o,function(){return this}),s(y,"toString",function(){return"[object Generator]"}),t.keys=function(e){var t=Object(e),n=[];for(var r in t)n.push(r);return n.reverse(),function e(){for(;n.length;){var r=n.pop();if(r in t)return e.value=r,e.done=!1,e}return e.done=!0,e}},t.values=U,j.prototype={constructor:j,reset:function(t){if(this.prev=0,this.next=0,this.sent=this._sent=e,this.done=!1,this.delegate=null,this.method="next",this.arg=e,this.tryEntries.forEach(w),!t)for(var n in this)"t"===n.charAt(0)&&r.call(this,n)&&!isNaN(+n.slice(1))&&(this[n]=e)},stop:function(){this.done=!0;var e=this.tryEntries[0].completion;if("throw"===e.type)throw e.arg;return this.rval},dispatchException:function(t){if(this.done)throw t;var n=this;function a(r,a){return i.type="throw",i.arg=t,n.next=r,a&&(n.method="next",n.arg=e),!!a}for(var c=this.tryEntries.length-1;c>=0;--c){var o=this.tryEntries[c],i=o.completion;if("root"===o.tryLoc)return a("end");if(o.tryLoc<=this.prev){var u=r.call(o,"catchLoc"),s=r.call(o,"finallyLoc");if(u&&s){if(this.prev<o.catchLoc)return a(o.catchLoc,!0);if(this.prev<o.finallyLoc)return a(o.finallyLoc)}else if(u){if(this.prev<o.catchLoc)return a(o.catchLoc,!0)}else{if(!s)throw Error("try statement without catch or finally");if(this.prev<o.finallyLoc)return a(o.finallyLoc)}}}},abrupt:function(e,t){for(var n=this.tryEntries.length-1;n>=0;--n){var a=this.tryEntries[n];if(a.tryLoc<=this.prev&&r.call(a,"finallyLoc")&&this.prev<a.finallyLoc){var c=a;break}}c&&("break"===e||"continue"===e)&&c.tryLoc<=t&&t<=c.finallyLoc&&(c=null);var o=c?c.completion:{};return o.type=e,o.arg=t,c?(this.method="next",this.next=c.finallyLoc,h):this.complete(o)},complete:function(e,t){if("throw"===e.type)throw e.arg;return"break"===e.type||"continue"===e.type?this.next=e.arg:"return"===e.type?(this.rval=this.arg=e.arg,this.method="return",this.next="end"):"normal"===e.type&&t&&(this.next=t),h},finish:function(e){for(var t=this.tryEntries.length-1;t>=0;--t){var n=this.tryEntries[t];if(n.finallyLoc===e)return this.complete(n.completion,n.afterLoc),w(n),h}},catch:function(e){for(var t=this.tryEntries.length-1;t>=0;--t){var n=this.tryEntries[t];if(n.tryLoc===e){var r=n.completion;if("throw"===r.type){var a=r.arg;w(n)}return a}}throw Error("illegal catch attempt")},delegateYield:function(t,n,r){return this.delegate={iterator:U(t),resultName:n,nextLoc:r},"next"===this.method&&(this.arg=e),h}},t}function k(e){var t=e.gameStateSetter,n="/functions/refill-fridge",a="/functions/soup-spices",c="/functions/check-soup",o={SHOW_ON_COUNTER:"Placing the current ingredient on the cooking space:",PUT_IN_POT:"Putting into the pot:",SIMMER:"Cooking!",ADD_TO_SALAD:"Adding to the salad:",MIX_SALAD:"Mixing the salad.",BLEND:"Blending.",ADD_TO_BLENDER:"Adding to the blender:",REMOVE_FROM_COUNTER:"Removing the current ingredient from the cooking space:",CUT_ON_COUNTER:"Cutting"},u={CITRUS_BASKET:"citrus basket",BERRY_BASKET:"berry basket",ROT_TOMATO:"rotten tomato",FRESH_TOMATO:"fresh tomato",CUT_TOMATO:"cut tomato",ROT_CUCUMBER:"rotten cucumber",FRESH_CUCUMBER:"fresh cucumber",CUT_CUCUMBER:"cut cucumber",ROT_CARROT:"rotten carrot",FRESH_CARROT:"fresh carrot",CUT_CARROT:"cut carrot",BERRY:"a berry",CITRUS:"an orange",SALT:"salt",PEPPER:"pepper",OREGANO:"oregano"},s="The fridge has been refilled!",l="Failed to refill the fridge. Check your implementation.",b="Not enough ingredients to cook",v="Cooking is complete!",C="Failed to cook. Check your implementation.",k="You need to cook the soup first!",w="Let's add some spices!",j="Spices have been added!",U="Failed to add spices. Check your implementation.",x="It tastes great! \ud83c\udf89",L="It tastes terrible... Please try cooking the soup again.",M="Failed to retrieve taste status. Check your implementation.",F=1e3,B={visible:!0,full:!1,shake:!1,berry:0,citrus:0},P={visible:!0,simmer:!1,soup:!1,pepper:!1,salt:!1,oregano:!1,cucumber:0,tomato:0,carrot:0,soupHue:0,spiced:!1},I={visible:!1,mixing:!1,mixed:!1,tomato:0,cucumber:0,carrot:0},D={type:"berry",visible:!1,count:5},H={type:"citrus",visible:!1,count:5};Object(r.useEffect)(function(){h.a.get("/functions/current-task").then(function(){var e=Object(m.a)(N().mark(function e(t){return N().wrap(function(e){for(;;)switch(e.prev=e.next){case 0:console.log("Current task type: ".concat(t.data)),we(t.data),je("SALAD"===t.data);case 3:case"end":return e.stop()}},e)}));return function(t){return e.apply(this,arguments)}}()).catch(function(e){Z("Can't retrieve task type. Please check the backend.")})},[]);var G=Object(r.useState)([]),Y=Object(i.a)(G,2),K=Y[0],q=Y[1],J=Object(r.useState)([]),z=Object(i.a)(J,2),V=z[0],W=z[1],X=Object(r.useState)("Press the button to start"),$=Object(i.a)(X,2),Q=$[0],Z=$[1],ee=Object(r.useState)(!0),te=Object(i.a)(ee,2),ne=te[0],re=(te[1],Object(r.useState)(B)),ae=Object(i.a)(re,2),ce=ae[0],oe=ae[1],ie=Object(r.useState)(P),ue=Object(i.a)(ie,2),se=ue[0],le=ue[1],pe=Object(r.useState)(I),fe=Object(i.a)(pe,2),me=fe[0],de=fe[1],be=Object(r.useState)(D),he=Object(i.a)(be,2),ve=he[0],Ae=he[1],ge=Object(r.useState)(H),Re=Object(i.a)(ge,2),Ee=Re[0],Oe=Re[1],ye=Object(r.useState)(""),Te=Object(i.a)(ye,2),Se=Te[0],Ce=Te[1],Ne=Object(r.useState)(""),ke=Object(i.a)(Ne,2),_e=ke[0],we=ke[1];function je(e){de(function(t){return Object(f.a)({},t,{visible:e})})}var Ue={BERRY_BASKET:function(e){Ae(function(t){return Object(f.a)({},t,{visible:e})})},CITRUS_BASKET:function(e){Oe(function(t){return Object(f.a)({},t,{visible:e})})},BLENDER:function(e){oe(function(t){return Object(f.a)({},t,{visible:e})})},POT:function(e){le(function(t){return Object(f.a)({},t,{visible:e})})}};var xe={FRESH_TOMATO:"CUT_TOMATO",FRESH_CUCUMBER:"CUT_CUCUMBER",FRESH_CARROT:"CUT_CARROT"};function Le(e){console.log("removeFromCounter",e),e&&(console.log("counter: ",K),console.log("filter: ",K.filter(function(t){return d[t]!==e})),q(function(t){var n=t.findIndex(function(t){return d[t]===e});if(n>-1){var r=Object(p.a)(t);return r.splice(n,1),r}return t}))}var Me={SHOW_ON_COUNTER:function(e){console.log("showOnCounter",e),e&&(e in Ue?Ue[e](!0):(function(e){console.log("removeFromFridge",e),e&&(console.log("counter: ",V),console.log("filter: ",V.filter(function(t){return d[t]!==e})),W(function(t){var n=t.findIndex(function(t){return d[t]===e});if(n>-1){var r=Object(p.a)(t);return r.splice(n,1),r}return t}))}(e),q(function(t){return[].concat(Object(p.a)(t),[d[e]])})))},PUT_IN_POT:function(e){null!=e&&(Le(e),{PEPPER:function(){le(function(e){return Object(f.a)({},e,{pepper:!0,soupHue:57})}),setTimeout(function(){le(function(e){return Object(f.a)({},e,{pepper:!1})})},750)},SALT:function(){le(function(e){return Object(f.a)({},e,{salt:!0,soupHue:320})}),setTimeout(function(){le(function(e){return Object(f.a)({},e,{salt:!1})})},750)},OREGANO:function(){le(function(e){return Object(f.a)({},e,{oregano:!0,soupHue:113})}),setTimeout(function(){le(function(e){return Object(f.a)({},e,{oregano:!1})})},750)},CUT_TOMATO:function(){le(function(e){return Object(f.a)({},e,{tomato:e.tomato+1})})},CUT_CARROT:function(){le(function(e){return Object(f.a)({},e,{carrot:e.carrot+1})})},CUT_CUCUMBER:function(){le(function(e){return Object(f.a)({},e,{cucumber:e.cucumber+1})})}}[e]())},SIMMER:function(e){le(function(e){return Object(f.a)({},e,{simmer:!0})}),setTimeout(function(){le(function(e){return Object(f.a)({},e,{soup:!0,soupHue:0})})},500),setTimeout(function(){le(function(e){return Object(f.a)({},e,{simmer:!1})})},1e3)},ADD_TO_SALAD:function(e){console.log("addToSalad()"),console.log(e),null!=e&&(Le(e),{CUT_TOMATO:function(){de(function(e){return Object(f.a)({},e,{tomato:e.tomato+1})})},CUT_CUCUMBER:function(){de(function(e){return Object(f.a)({},e,{cucumber:e.cucumber+1})})},CUT_CARROT:function(){de(function(e){return Object(f.a)({},e,{carrot:e.carrot+1})})}}[e]())},MIX_SALAD:function(e){de(function(e){return Object(f.a)({},e,{mixing:!0})}),setTimeout(function(){de(function(e){return Object(f.a)({},e,{mixed:!0})})},500),setTimeout(function(){de(function(e){return Object(f.a)({},e,{mixing:!1})})},1e3)},BLEND:function(e){oe(function(e){return Object(f.a)({},e,{shake:!0})}),setTimeout(function(){oe(function(e){return Object(f.a)({},e,{full:!0})})},500),setTimeout(function(){oe(function(e){return Object(f.a)({},e,{shake:!1})})},1e3)},ADD_TO_BLENDER:function(e){null!=e&&(Le(e),{CITRUS:function(){Oe(function(e){return Object(f.a)({},e,{count:e.count>0?e.count-1:0})}),oe(function(e){return Object(f.a)({},e,{citrus:e.citrus+1})})},BERRY:function(){Ae(function(e){return Object(f.a)({},e,{count:e.count>0?e.count-1:0})}),oe(function(e){return Object(f.a)({},e,{berry:e.berry+1})})}}[e]())},REMOVE_FROM_COUNTER:Le,CUT_ON_COUNTER:function(e){if(console.log("cutOnCounter",e),e&&(console.log("counter: ",K),xe.hasOwnProperty(e))){var t=xe[e];console.log("counter: ",K),console.log("filter: ",K.filter(function(t){return d[t]!==e})),q(function(n){var r=n.findIndex(function(t){return d[t]===e});if(r>-1){var a=Object(p.a)(n);return a.splice(r,1,d[t]),a}return n})}}};function Fe(e,t){var n=arguments.length>2&&void 0!==arguments[2]?arguments[2]:"";oe(B),le(P),de(I),je("SALAD"===_e),Oe(H),Ae(D),q([]),Ce(t);var r=function(e){return new Promise(function(t){return setTimeout(t,e)})},a=Array();h.a.get(e).then(function(){var e=Object(m.a)(N().mark(function e(c){var i,s,l;return N().wrap(function(e){for(;;)switch(e.prev=e.next){case 0:if(console.log("GOT: "+JSON.stringify(c.data,null,2)),0!=(a=c.data).length){e.next=6;break}return Z("".concat(b," ").concat(t,"! ").concat(n)),Ce(""),e.abrupt("return");case 6:return Z("Let's go!"),e.next=9,r(F);case 9:i=S(a),e.prev=10,i.s();case 12:if((s=i.n()).done){e.next=21;break}return l=s.value,console.log(o[String(l.type)]+" "+(l.parameter?u[String(l.parameter)]:"")),Z(o[String(l.type)]+" "+(l.parameter?u[String(l.parameter)]:"")),Me[String(l.type)](l.parameter?String(l.parameter):null),e.next=19,r(F);case 19:e.next=12;break;case 21:e.next=26;break;case 23:e.prev=23,e.t0=e.catch(10),i.e(e.t0);case 26:return e.prev=26,i.f(),e.finish(26);case 29:Z(v);case 30:case"end":return e.stop()}},e,null,[[10,23,26,29]])}));return function(t){return e.apply(this,arguments)}}()).catch(function(e){Z(C)}).finally(function(){Ce("")})}function Be(e){return!Se}return React.createElement("div",{className:"App-main-container"},React.createElement("div",{className:"App-buttons-container"},React.createElement("button",{className:"App-button-base App-game-button-bottom-base App-button-back",onClick:function(){t(_.START)}})),React.createElement("div",{className:"App-kitchen-space"},React.createElement("div",{className:"App-info-container"},React.createElement("div",{className:"App-info-container-text font-link-base"},Q)),React.createElement("div",{className:"App-kitchen-container"},React.createElement("div",{className:"App-left-kitchen-container"},React.createElement(O,{visible:ne}),React.createElement(T,{products:V})),React.createElement("div",{className:"App-center-kitchen-container"},React.createElement("div",{className:"App-basket-kitchen-container"},React.createElement(E,{basketOptions:ve}),React.createElement(y,{saladBowlOptions:me}),React.createElement(E,{basketOptions:Ee})),React.createElement(A,{products:K})),React.createElement("div",{className:"App-right-kitchen-container"},React.createElement(g,{blenderOptions:ce}),React.createElement(R,{potOptions:se})))),React.createElement("div",{className:"App-buttons-container"},("SOUP"===_e||"SALAD"===_e)&&React.createElement("button",{className:"App-button-base App-button-action "+(Be()?"":"App-button-disable"),onClick:function(){return function(){oe(B),le(P),de(I),je("SALAD"===_e),Oe(H),Ae(D),q([]);var e=Array();h.a.get(n).then(function(){var t=Object(m.a)(N().mark(function t(n){var r;return N().wrap(function(t){for(;;)switch(t.prev=t.next){case 0:r=n.data,e=r.map(function(e){return d[e]}),console.log("Refill GOT: "+e),W(e),Z(s);case 5:case"end":return t.stop()}},t)}));return function(e){return t.apply(this,arguments)}}()).catch(function(e){Z(l)})}()}},"Refill"),"SOUP"===_e&&React.createElement(React.Fragment,null,React.createElement("button",{className:"App-button-base App-button-action "+(V.length>0&&Be()?"":"App-button-disable"),onClick:function(){return Fe("/functions/tomato-soup","tomato soup","You need at least three fresh tomatoes in the fridge! Please refill!")}},"Soup"),React.createElement("button",{className:"App-button-base App-button-action "+(se.soup&&!se.spiced&&Be()?"":"App-button-disable"),onClick:function(){return function(){Ce("spice");var e=function(e){return new Promise(function(t){return setTimeout(t,e)})},t=Array();h.a.get(a).then(function(){var n=Object(m.a)(N().mark(function n(r){var a,c,i;return N().wrap(function(n){for(;;)switch(n.prev=n.next){case 0:if(t=r.data,console.log("GOT: "+t),0!=t.length){n.next=6;break}return Z(k),Ce(""),n.abrupt("return");case 6:return Z(w),n.next=9,e(F);case 9:a=S(t),n.prev=10,a.s();case 12:if((c=a.n()).done){n.next=21;break}return i=c.value,console.log(o[String(i.type)]+" "+(i.parameter?u[String(i.parameter)]:"")),Z(o[String(i.type)]+" "+(i.parameter?u[String(i.parameter)]:"")),Me[String(i.type)](i.parameter?String(i.parameter):null),n.next=19,e(F);case 19:n.next=12;break;case 21:n.next=26;break;case 23:n.prev=23,n.t0=n.catch(10),a.e(n.t0);case 26:return n.prev=26,a.f(),n.finish(26);case 29:Z(j),le(function(e){return Object(f.a)({},e,{spiced:!0})});case 31:case"end":return n.stop()}},n,null,[[10,23,26,29]])}));return function(e){return n.apply(this,arguments)}}()).catch(function(e){Z(U)}).finally(function(){Ce("")})}()}},"Spice"),React.createElement("button",{className:"App-button-base App-button-action "+(se.soup&&se.spiced&&Be()?"":"App-button-disable"),onClick:function(){h.a.get(c).then(function(){var e=Object(m.a)(N().mark(function e(t){var n;return N().wrap(function(e){for(;;)switch(e.prev=e.next){case 0:n=t.data,console.log("isTasteGood: "+n),Z(n?x:L);case 3:case"end":return e.stop()}},e)}));return function(t){return e.apply(this,arguments)}}()).catch(function(e){Z(M)})}},"Taste")),"SALAD"===_e&&React.createElement(React.Fragment,null,React.createElement("button",{className:"App-button-base App-button-action-wide "+(V.length>0&&Be()?"":"App-button-disable"),onClick:function(){return Fe("/functions/salad-list","salad (list)","Please refill the fridge!")}},"Salad list"),React.createElement("button",{className:"App-button-base App-button-action-wide "+(V.length>0&&Be()?"":"App-button-disable"),onClick:function(){return Fe("/functions/salad-sequence","salad (sequence)","Please refill the fridge!")}},"Salad seq.")),"SMOOTHIE"===_e&&React.createElement("button",{className:"App-button-base App-button-action-wide "+(Be()?"":"App-button-disable"),onClick:function(){return Fe("/functions/smoothie","smoothie")}},"Smoothie")))}window.React=a.a;var _=function(e){return e[e.START=0]="START",e[e.GAME=1]="GAME",e}({});function w(e){var t=e.state,n=e.gameStateSetter;switch(t){case _.START:return a.a.createElement(l,{gameStateSetter:n});case _.GAME:return a.a.createElement(k,{gameStateSetter:n})}}var j=function(){var e=Object(r.useState)(_.START),t=Object(i.a)(e,2),n=t[0],a=t[1];switch(n){case _.START:return React.createElement("div",{className:"App"},React.createElement("header",{className:"App-header-base App-header-black"},React.createElement(w,{state:n,gameStateSetter:a})));default:return React.createElement("div",{className:"App"},React.createElement("header",{className:"App-header-base App-header-white"},React.createElement(w,{state:n,gameStateSetter:a})))}},U=function(e){e&&e instanceof Function&&n.e(3).then(n.bind(null,56)).then(function(t){var n=t.getCLS,r=t.getFID,a=t.getFCP,c=t.getLCP,o=t.getTTFB;n(e),r(e),a(e),c(e),o(e)})};h.a.defaults.baseURL="/api/",o.a.createRoot(document.getElementById("root")).render(a.a.createElement(a.a.StrictMode,null,a.a.createElement(j,null))),U()}},[[23,1,2]]]);
//# sourceMappingURL=main.2934fd44.chunk.js.map