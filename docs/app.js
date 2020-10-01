function block(title, pdf) {

    return "<li class='sgLi'>\
                <div class='box'>\
                    <h3> " + title + "</h3>\
                    <ul class='df'>\
                        <li><a href='" + pdf + "'> PDF </a></li>\
                    </ul>\
                </div>\
            </li>"; 

}



var anno1 = [
    {"title": "Algebra lineare e Geometria", "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Primo%20Anno/Algebra%20Lineare%20e%20Geometria/Algebra-geometria.pdf"},
    {"title": "Algoritmi e Strutture Dati", "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Primo%20Anno/Algoritmi%20e%20Strutture%20Dati/algoritmi.pdf"},
    {"title": "Analisi Matematica", "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Primo%20Anno/Analisi%20Matematica/analisi.pdf"},
    {"title": "Fondamenti di Informatica", "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Primo%20Anno/Fondamenti/appunti.pdf"}
]
var anno2 = [
    {"title": "Linguaggi di Programmazione", "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Secondo%20Anno/Linguaggi%20di%20Programmazione/linguaggi-di-programmazione.pdf"},
    {"title": "Linguaggi e Computabilità", "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Secondo%20Anno/Linguaggi%20e%20Computabilit%C3%A0/linguaggi-e-computabilit%C3%A0.pdf"},
    {"title": "Metodi algebrici per l'Informatica", "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Secondo%20Anno/Metodi%20algebrici%20per%20l'informatica/metodi-algebrici-per-informatica.pdf"},
    {"title": "Probabilità e Statistica per l'Informatica", "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Secondo%20Anno/Probabilit%C3%A0%20e%20Statistica%20per%20l'Informatica/statistica.pdf"},
    {"title": "Reti e Sistemi Operativi", "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Secondo%20Anno/Reti%20e%20Sistemi%20Operativi/rso.pdf"}
]
var anno3 = [
    {"title": "Analisi e progetto di Algoritmi", "pdf": ""},
    {"title": "Elementi di Bioinformatica", "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Terzo%20Anno/Elementi%20di%20Bioinformatica/bioinformatica.pdf"},
    {"title": "Metodi Formali", "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Terzo%20Anno/Metodi%20Formali/metodi.pdf"},
    {"title": "Ricerca Operativa e Pianificazione delle risorse", "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Terzo%20Anno/Ricerca%20Operativa%20e%20Pianificazione%20delle%20Risorse/ro.pdf"}
]
var anno4 = []
var anno5 = []

anno1.forEach(element => {
    document.getElementById("anno1").innerHTML += block(element.title, element.pdf)
});
anno2.forEach(element => {
    document.getElementById("anno2").innerHTML += block(element.title, element.pdf)
});
anno3.forEach(element => {
    document.getElementById("anno3").innerHTML += block(element.title, element.pdf)
});
anno4.forEach(element => {
    document.getElementById("anno4").innerHTML += block(element.title, element.pdf)
});
anno5.forEach(element => {
    document.getElementById("anno5").innerHTML += block(element.title, element.pdf)
});