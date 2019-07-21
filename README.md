# REST-RTLS
Dokumentation

How to use
Zum starten der Anwendung MQTTClient ausführen.
MQTT Broker IP in Zeile 21 festlegen.
Z.86 Topics festlegen auf die gehört wird. // Standart ist Lights und SendPlayerData

Wenn eine Nachricht empfangen wird, wird auf Answer1 2 und 3 gehört.

Answer1 Fragt im RTLS System das Feld mit der id 14 ab.
Answer2 Fragt im RTLS System das Feld mit der id 15 ab.
Answer3 Fragt im RTLS System das Feld mit der id 16 ab.

Danach wird eine Antwort(Array mit allen Badge-Daten aus dem Feld) generiert und in den Channel "GetWinner" geschickt.



Wenn eine Nachricht empfangen wird, welche den String "Player" enthält, wird eine Payload generiert und das RTLS Feld 17 abgefragt.
Die Badge welche sich im Feld 17 befindet, wird mit den neuen Daten beschrieben.


// Bitte immer nur eine Badge in das Anmeldungsfeld legen. Es wurde nicht getestet was bei mehreren Badges passiert....

Ist die Badge neu beschrieben wird eine Antwort generiert welche den Spielernamen und seine Badge-ID beinhalten. Diese Antowert wird in "SetUser" gepublished.


Änderungen an der Verbindung(Authentifizierung) am RTLS System bitte in OAuthconfig vornehmen.
