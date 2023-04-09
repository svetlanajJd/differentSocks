package service.impl;

import entity.Socks;
import exceptions.CreateSocksException;
import exceptions.SocksNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import repository.SocksRepository;
import service.SocksService;

import java.util.List;

@Service
public class SocksServiceImpl implements SocksService {
    private final SocksRepository socksRepository;
    Logger logger = LoggerFactory.getLogger(SocksService.class);

    public SocksServiceImpl(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    @Override
    public Socks createSocks(Socks socks) {
        if (socks.getCottonPart() >= 0 && socks.getCottonPart() <= 100) {
            logger.info("Was invoked method for create socks");
            return socksRepository.save(socks);
        } else {
            throw new CreateSocksException();
        }
    }

    @Override
    public void deleteSocks(Socks socks) {
        logger.warn("There is socks with color = {}", socks.getColor() + " and cottonPart = {}", socks.getCottonPart() + " was delete");
        socksRepository.deleteSocksByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
    }

    @Override
    public Long findSocks(String color, Long cottonPart, String operation) {

        switch (operation) {
            case "moreThan" -> {
                List<Socks> map = socksRepository.findSocksByColor(color)
                        .stream().toList()
                        .stream().filter(socks1 -> socks1.getCottonPart() > cottonPart).toList();
                return Long.valueOf(map.size());
            }
            case "lessThan" -> {
                List<Socks> map = socksRepository.findSocksByColor(color)
                        .stream().toList()
                        .stream().filter(socks1 -> socks1.getCottonPart() < cottonPart).toList();
                return Long.valueOf(map.size());
            }
            case "equals" -> {
                List<Socks> map = socksRepository.findSocksByColor(color)
                        .stream().toList()
                        .stream().filter(socks1 -> socks1.getCottonPart().equals(cottonPart)).toList();
                return Long.valueOf(map.size());
            }
        }
        logger.info("Socks found");
        throw new SocksNotFoundException();

    }
}
