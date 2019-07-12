package xyz.migoo.web.api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.migoo.web.api.util.Md5;
import xyz.migoo.web.dao.UserDAO;
import xyz.migoo.web.dto.UserDTO;
import xyz.migoo.web.po.UserPO;
import xyz.migoo.web.pojo.ResponseBean;
import xyz.migoo.web.vo.UserVO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @author xiaomi
 * @date 2018/09/22
 */
@Service
public class UserService {

    private static final Pattern PATTERN = Pattern.compile("/^\\d+$/");

    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public ResponseBean register(UserDTO userDTO){
        try {
            UserVO userVO = userDAO.selectByName(userDTO.getUserName());
            if (userVO != null){
                return new ResponseBean().setStatus(412).setData("user exist");
            }
            this.passwordCheck(userDTO.getPassword());
            String salt = this.getSalt();
            String password = this.getPassword(userDTO.getPassword(), salt);
            UserPO userPO = new UserPO();
            userPO.setSalt(salt);
            userPO.setUserName(userDTO.getUserName());
            userPO.setPassword(password);
            this.insert(userPO);
            return new ResponseBean().setStatus(200).setData(userDTO);
        }catch (Exception e){
            return new ResponseBean().setStatus(412).setData(e.getMessage());
        }
    }

    public UserVO loginCheck(UserDTO userDTO) throws Exception{
        String sign = Md5.md5(userDTO.getUserName() + userDTO.getPassword());
        if (!userDTO.getSign().equals(sign)){
            throw new Exception("sign validate error");
        }
        UserVO userVO = userDAO.selectByName(userDTO.getUserName());
        if (userVO == null) {
            throw new Exception("username or password error");
        }
        String password = this.getPassword(userDTO.getPassword(), userVO.getSalt());
        if (!password.equals(userVO.getPassword())){
            throw new Exception("username or password error");
        }
        return userVO;
    }

    public String getToken(UserVO user) {

        String token = JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + (24 * 60 * 60* 1000)))
                .withAudience(user.getId().toString())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

    private void passwordCheck(String password) throws Exception{
        if (PATTERN.matcher(password).find()){
            throw new Exception("password is too easy");
        }
    }

    private String getSalt(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    private String getPassword(String pwd, String salt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] bs = digest.digest((pwd + salt).getBytes());
        String password = new String(new Hex().encode(bs));
        char[] cs = new char[48];
        for (int i = 0; i < cs.length; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return new String(cs);
    }

    public UserVO selectById(Long id){
        return userDAO.selectById(id);
    }

    private int insert(UserPO userPO){
        userPO.setId(System.currentTimeMillis());
        userPO.setIsDelete(0);
        userPO.setCreateAt(new Date());
        userPO.setModifyAt(new Date());
        return userDAO.insert(userPO);
    }
}
