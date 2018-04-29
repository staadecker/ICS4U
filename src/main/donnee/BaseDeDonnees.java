/*
 * MIT License
 *
 * Copyright (c) 2018 Martin Staadecker
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package main.donnee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.NotNull;

public class BaseDeDonnees {
    private ObservableList<Client> clients = FXCollections.observableArrayList();

    private DataAccess dataAccess;

    private int nextID = 0;

    public BaseDeDonnees(@NotNull DataAccess dataLoader) {
        this.dataAccess = dataLoader;

        for (Client client : dataLoader.load()) {
            clients.add(client);
            nextID = Math.max(nextID, client.getId() + 1);
        }
    }

    public void ajouter(ClientInfo info) {
        clients.add(new Client(nextID++, info));
        dataAccess.write(clients);
    }

    public void supprimer(int index) {
        clients.remove(index);
        dataAccess.write(clients);
    }

    public ObservableList<Client> getClients() {
        return clients;
    }
}
