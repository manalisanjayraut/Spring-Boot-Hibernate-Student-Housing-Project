
package com.mycompany.studenthousingmanagement.DAO;


import com.mycompany.studenthousingmanagement.Model.User;

/**
 *
 * @author manal
 */
interface UserDAOInterface {

    public void saveUser(User user);

    public User findByUsername(String username);
    
    public User login(String username, String password);
    
    

}
