package service;

import entity.Socks;

public interface SocksService {
    public Socks createSocks(Socks socks);

    void deleteSocks(Socks socks);

    public Long findSocks(String color, Long cottonPart, String operation);
}
