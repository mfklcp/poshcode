/*
 * @Autor: Márcio Franklin
 * @E-mail: mfklcp@gmail.com
 * @License: MIT
 *
 */

package mfklcp.monografia.thejugde.Service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
