package com.example.YBO.model;


import com.example.YBO.status.RequestStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

//entity that is given to company.
//it has CV entity by ManyToOne relationship because user can send multiple job requests
//butt it needs to be sent with same CV every time.
//also it is joined with opening entity witch is connected by ManyToOne relationship because
//it should be able to send one request to multiple different company's openings.
//and about status: it's filter for company. status could be
//"pending", "approved" or "disapproved".

@Getter
@Setter
@Builder
@Entity
@Table(name = "job_requests")
@NoArgsConstructor
@AllArgsConstructor
public class JobRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cv_id")
    private CV cv;

    @ManyToOne
    @JoinColumn(name = "opening_id")
    private Opening opening;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;
}


