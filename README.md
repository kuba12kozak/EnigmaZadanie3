# EnigmaZadanie3
Zadanie rekrutacyjne do firmy Enigma

Aby użyć biblioteki trzeba ją najpierw skompilować.
Do tego celu należy wykonać komendę:

mvn clean package.

Po skompilowaniu w katalogu "target" znajdzie się plik EnigmaZadanie3-jar-with-dependencies.jar

Jest to plik, który należy dołączyć do swojego projektu.

Biblioteka zawiera klasę InvoicesHandlerStripeFactory. 
Posiada ona metodę createInvoiceHandlerStripe, która tworzy obiekt InvoicesHandlerStripe.
Klasa ta implementuje interfejs InvoicesHandler. Zawier on dwie metody:
- createInvoice
- getInvoiceById

Argumenty, które przyjmują opisane są w javadoc w interfejsie InvoicesHandler.