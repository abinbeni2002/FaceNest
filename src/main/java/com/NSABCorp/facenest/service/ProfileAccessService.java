package com.NSABCorp.facenest.service;

import com.NSABCorp.facenest.Repositories.ProfileRepository;
import com.NSABCorp.facenest.exception.ResourceNotFoundException;
import com.NSABCorp.facenest.model.Profile;
import com.NSABCorp.facenest.model.ProfileRequestDTO;
import com.NSABCorp.facenest.model.ProfileResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileAccessService {
    @Autowired
    private ProfileRepository profilerepository;

    public ResponseEntity<ProfileResponseDTO> saveProfile(ProfileRequestDTO profiledto)
    {
        Profile profile=new Profile();
        profile.setName(profiledto.getName());
        profile.setEmail(profiledto.getEmail());
        profilerepository.save(profile);
        ProfileResponseDTO profileResponseDTO =new ProfileResponseDTO(profile.getId(),profile.getName(),profile.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(profileResponseDTO);
    }
    public ResponseEntity<ProfileResponseDTO> getProfileById(Long id)
    {
        ProfileResponseDTO profileResponse;
        Optional<Profile> profile = Optional.of(profilerepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Profile not found with id " + id)));
        profileResponse=profile.get().convertToResponse();
        return ResponseEntity.ok(profileResponse);
    }

    public List<ProfileResponseDTO> getAllProfiles() {
        List<ProfileResponseDTO> profileResponseDTOList=new ArrayList<>();
        List<Profile> profileList=profilerepository.findAll();
        for(Profile profile: profileList)
        {
            ProfileResponseDTO profileResponseDTO=new ProfileResponseDTO();
            profileResponseDTO.setName(profile.getName());
            profileResponseDTO.setEmail(profile.getEmail());
            profileResponseDTO.setId(profile.getId());
            profileResponseDTOList.add(profileResponseDTO);
        }
        return profileResponseDTOList;
    }

    public void deleteProfile(Long id) {
        Profile profile=profilerepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Profile not found with id " + id));
        profilerepository.delete(profile);
    }

    public ProfileResponseDTO updateProfile(Long id, ProfileRequestDTO profileRequestDTO) {
        Optional<Profile> p = Optional.of(profilerepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Profile not found with id " + id)));
        Profile profile=p.get();
        profile.setId(id);
        profile.setName(profileRequestDTO.getName());
        profile.setEmail(profileRequestDTO.getEmail());
        profilerepository.save(profile);
        return profile.convertToResponse();
    }
}
