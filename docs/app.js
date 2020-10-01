function box(title, pdf, source) {

    pdf_item    = (pdf != "") ? "<li><a href='" + pdf + "'> PDF </a></li>" : "";
    source_item = (source != "") ? "<li><a href='" + source + "'> Risorse </a></li>" : "";

    return "<li class='sgLi'>\
                <div class='box'>\
                    <h3> " + title + "</h3>\
                    <ul class='df'>\
                        " + pdf_item + "\
                        " + source_item + "\
                    </ul>\
                </div>\
            </li>"; 

}



var anno1 = [
    {"title": "Algebra lineare e Geometria", "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Primo%20Anno/Algebra%20Lineare%20e%20Geometria/Algebra-geometria.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Triennale/Primo%20Anno/Algebra%20Lineare%20e%20Geometria"},
    {"title": "Algoritmi e Strutture Dati", "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Primo%20Anno/Algoritmi%20e%20Strutture%20Dati/algoritmi.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Triennale/Primo%20Anno/Algoritmi%20e%20Strutture%20Dati"},
    {"title": "Analisi Matematica", "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Primo%20Anno/Analisi%20Matematica/analisi.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Triennale/Primo%20Anno/Analisi%20Matematica"},
    {"title": "Fondamenti di Informatica", "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Primo%20Anno/Fondamenti/appunti.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Triennale/Primo%20Anno/Fondamenti"}
]
var anno2 = [
    {"title": "Linguaggi di Programmazione", "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Secondo%20Anno/Linguaggi%20di%20Programmazione/linguaggi-di-programmazione.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Triennale/Secondo%20Anno/Linguaggi%20di%20Programmazione"},
    {"title": "Linguaggi e Computabilità", "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Secondo%20Anno/Linguaggi%20e%20Computabilit%C3%A0/linguaggi-e-computabilit%C3%A0.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Triennale/Secondo%20Anno/Linguaggi%20e%20Computabilit%C3%A0"},
    {"title": "Metodi algebrici per l'Informatica", "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Secondo%20Anno/Metodi%20algebrici%20per%20l'informatica/metodi-algebrici-per-informatica.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Triennale/Secondo%20Anno/Metodi%20algebrici%20per%20l'informatica"},
    {"title": "Probabilità e Statistica per l'Informatica", "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Secondo%20Anno/Probabilit%C3%A0%20e%20Statistica%20per%20l'Informatica/statistica.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Triennale/Secondo%20Anno/Probabilit%C3%A0%20e%20Statistica%20per%20l'Informatica"},
    {"title": "Sistemi Distribuiti", "pdf": "", "source": "https://github.com/dlcgold/Appunti/tree/master/Triennale/Secondo%20Anno/Sistemi%20Distribuiti"},
    {"title": "Reti e Sistemi Operativi", "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Secondo%20Anno/Reti%20e%20Sistemi%20Operativi/rso.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Triennale/Secondo%20Anno/Reti%20e%20Sistemi%20Operativi"}
]
var anno3 = [
    {"title": "Analisi e progetto di Algoritmi", "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Terzo%20Anno/Analisi%20e%20Progetto%20di%20Algoritmi/algoritmi2.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Triennale/Terzo%20Anno/Analisi%20e%20Progetto%20di%20Algoritmi"},
    {"title": "Elementi di Bioinformatica", "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Terzo%20Anno/Elementi%20di%20Bioinformatica/bioinformatica.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Triennale/Terzo%20Anno/Elementi%20di%20Bioinformatica"},
    {"title": "Metodi Formali", "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Terzo%20Anno/Metodi%20Formali/metodi.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Triennale/Terzo%20Anno/Metodi%20Formali"},
    {"title": "Ricerca Operativa e Pianificazione delle risorse", "pdf": "https://github.com/dlcgold/Appunti/raw/master/Triennale/Terzo%20Anno/Ricerca%20Operativa%20e%20Pianificazione%20delle%20Risorse/ro.pdf", "source": "https://github.com/dlcgold/Appunti/tree/master/Triennale/Terzo%20Anno/Ricerca%20Operativa%20e%20Pianificazione%20delle%20Risorse"},
    {"title": "Programmazione C++", "pdf": "", "source": "https://github.com/dlcgold/Appunti/tree/master/Triennale/Terzo%20Anno/Programmazione%20C%2B%2B"}
]
var anno4 = []
var anno5 = []

anno1.forEach(element => {
    document.getElementById("anno1").innerHTML += box(element.title, element.pdf, element.source)
});
anno2.forEach(element => {
    document.getElementById("anno2").innerHTML += box(element.title, element.pdf, element.source)
});
anno3.forEach(element => {
    document.getElementById("anno3").innerHTML += box(element.title, element.pdf, element.source)
});
anno4.forEach(element => {
    document.getElementById("anno4").innerHTML += box(element.title, element.pdf, element.source)
});
anno5.forEach(element => {
    document.getElementById("anno5").innerHTML += box(element.title, element.pdf, element.source)
});